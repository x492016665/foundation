package com.pf.config;


import com.pf.utils.exception.SystemException;
import com.pf.utils.result.Result;
import com.pf.utils.result.Status;
import com.pf.utils.result.StatusCode;
import com.pf.utils.result.WebMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.models.*;
import io.swagger.models.properties.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.web.SwaggerTransformationContext;
import springfox.documentation.swagger2.web.WebMvcSwaggerTransformationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yk_xing
 */
@RestControllerAdvice
public class WebMessageAdvice implements ResponseBodyAdvice, WebMvcSwaggerTransformationFilter {

    //com.pf.xxxx -> pf
    private final String projectIdentify = this.getClass().getName().split("\\.")[1];

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //排除其他模块的路径，例如swagger的请求
        return returnType.getExecutable().getDeclaringClass().getName().indexOf(projectIdentify) > 0;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CharSequence) {
            return body;
        }
        if (body instanceof Status) {
            Status status = (Status) body;
            Object res = WebMessage.fail(status);
            return res;
        }
        return WebMessage.success(body);
    }

    @Override
    public Swagger transform(SwaggerTransformationContext<HttpServletRequest> context) {
        Swagger swagger = context.getSpecification();
        doTransform(swagger);
        return swagger;
    }

    private void doTransform(Swagger swagger) {
        Map<String, Model> definitions = swagger.getDefinitions();
        Model resultModel = definitions.get("请求结果");
        Map<String, Path> paths = swagger.getPaths();
        for (Map.Entry<String, Path> entry : paths.entrySet()) {
            Path path = entry.getValue();
            List<Operation> operations = path.getOperations();
            for (Operation op : operations) {
                Response response = op.getResponses().get("200");
                Model resultDefition = createResultDefition(response, resultModel);
                definitions.put(resultDefition.getTitle(), resultDefition);
                response.setSchema(null);
            }
        }
    }

    public Model createResultDefition(Response response, Model resultModel) {
        Model model = response.getResponseSchema();
        if (model == null) {
            RefModel resultRef = new RefModel();
            resultRef.setReference(resultModel.getTitle());
            response.setResponseSchema(resultRef);
            return resultModel;
        }
        resultModel = (Model) resultModel.clone();
        Map<String, Property> properties = resultModel.getProperties();
        String resultName;
        ApiModel apiModel = Result.class.getAnnotation(ApiModel.class);
        if (apiModel == null) {
            resultName = Result.class.getName();
        } else {
            resultName = apiModel.value();
        }
        String refName = getResponseReference(model);
        resultModel.setReference(refName);
        if (model instanceof ArrayModel) {
            resultModel.setTitle(resultName + "«List«" + refName + "»»");
            ArrayProperty ref = new ArrayProperty();
            RefProperty property = new RefProperty();
            property.set$ref(refName);
            ref.setItems(property);
            ref.setDescription("返回数据对象");
            properties.put("data", ref);
        } else {
            resultModel.setTitle(resultName + "«" + refName + "»");
            RefProperty ref = new RefProperty();
            ref.set$ref(refName);
            ref.setDescription("返回数据对象");
            properties.put("data", ref);
        }
        resultModel.setProperties(properties);
        //创建path指向model
        RefModel resultRef = new RefModel();
        resultRef.setReference(resultModel.getTitle());
        response.setResponseSchema(resultRef);
        return resultModel;
    }

    public String getResponseReference(Model model) {
        if (model instanceof ArrayModel) {
            ArrayModel arrayModel = (ArrayModel) model;
            Property property = arrayModel.getItems();
            if (property instanceof RefProperty) {
                RefProperty ref = (RefProperty) property;
                return ref.getOriginalRef();
            }
            if (property instanceof AbstractProperty) {
                AbstractProperty ref = (AbstractProperty) property;
                return ref.getFormat();
            }
            throw new SystemException(StatusCode.SWAGGER_TRANSFORMATION_NULL);
        }
        if (model instanceof RefModel) {
            RefModel refModel = (RefModel) model;
            return refModel.getOriginalRef();
        }
        if (model instanceof ModelImpl) {
            ModelImpl modelImpl = (ModelImpl) model;
            return modelImpl.getFormat();
        }
        throw new SystemException(StatusCode.SWAGGER_TRANSFORMATION_NULL);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
