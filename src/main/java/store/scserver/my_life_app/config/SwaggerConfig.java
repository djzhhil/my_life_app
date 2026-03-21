package store.scserver.my_life_app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger API 文档配置
 *
 * 访问地址：
 * - UI: http://localhost:8080/swagger-ui.html
 * - JSON: http://localhost:8080/v3/api-docs
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置 OpenAPI 基本信息
     */
    @Bean
    public OpenAPI myLifeAppOpenAPI() {
        return new OpenAPI()
                // API 基本信息
                .info(new Info()
                        .title("MyLifeApp API")
                        .description("个人成长养成游戏 API 文档\n\n## 功能说明\n- **JWT 认证**：基于 Bearer Token 的用户认证\n- **任务管理**：创建、完成、删除任务，支持分类、优先级、截止日期\n- **记账功能**：记录收入/支出，支持分类管理\n- **心愿单**：设定心愿目标、存钱进度跟踪、购买打卡\n\n## 认证说明\n除了 `/api/user/register` 和 `/api/user/login` 外，所有接口都需要在 Header 中携带 JWT Token：\n```\nAuthorization: Bearer {your_jwt_token}\n```\n\nJWT Token 从登录接口的 `data.token` 字段获取。")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("MyLifeApp Team")
                                .email("support@mylifeapp.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                
                // JWT 认证配置
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT Token 认证。从 `/api/user/login` 接口获取 Token，然后在 Header 中添加：`Authorization: Bearer {token}`")))
                
                // 默认所有接口都需要认证（除了登录/注册）
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}
