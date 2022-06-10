package com.segroup.hospitalsite;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author zheng
 * @date 2022-03-30
 */
public class CodeGeneratorTest {
    /**
     * 执行初始化数据库脚本
     */
//    @BeforeAll
    public static void before() throws SQLException {
        Connection conn = DATA_SOURCE_CONFIG.getConn();
        InputStream inputStream = CodeGeneratorTest.class.getResourceAsStream("/sql/init.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://124.223.201.95:3316/hospital_info?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useInformationSchema=true",
            "hospital_info", "hospital")
            .build();

    /**
     * 策略配置
     */
    private StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder(); // 设置需要生成的表名
    }

    /**
     * 全局配置
     */
    private GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder()
                .author("who")
                .outputDir(System.getProperty("user.dir")+"/src/main/java")
                .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期
                .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                .fileOverride()   //覆盖之前的文件
                .enableSwagger()   //开启 swagger 模式
                .disableOpenDir();   //禁止打开输出目录，默认打开
    }

    /**
     * 包配置
     */
    private PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder();
//        return new PackageConfig.Builder().parent("com.segroup.hospitalsite")
//                .moduleName("mbg")
//                .entity("entity")   //pojo 实体类包名
//                .service("service") //Service 包名
//                .serviceImpl("serviceImpl") // ***ServiceImpl 包名
//                .mapper("mapper")   //Mapper 包名
//                .xml("mapper")  //Mapper XML 包名
//                .controller("controller") //Controller 包名
//                .other("utils") //自定义文件包名
//                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
    }

    /**
     * 模板配置
     */
    private TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder();
    }

    /**
     * 注入配置
     */
    private InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }

    /**
     * 简单生成
     */
    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 过滤表前缀（后缀同理，支持多个）
     * result: t_simple -> simple
     */
    @Test
    public void testTablePrefix() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().addTablePrefix("t_", "c_").build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 过滤字段后缀（前缀同理，支持多个）
     * result: deleted_flag -> deleted
     */
    @Test
    public void testFieldSuffix() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().addFieldSuffix("_flag").build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 乐观锁字段设置
     * result: 新增@Version注解
     * 填充字段设置
     * result: 新增@TableField(value = "xxx", fill = FieldFill.xxx)注解
     */
    @Test
    public void testVersionAndFill() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().entityBuilder()
                .versionColumnName("version") // 基于数据库字段
                .versionPropertyName("version")// 基于模型属性
                .addTableFills(new Column("create_time", FieldFill.INSERT))    //基于数据库字段填充
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))    //基于模型属性填充
                .build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 逻辑删除字段设置
     * result: 新增@TableLogic注解
     * 忽略字段设置
     * result: 不生成
     */
    @Test
    public void testLogicDeleteAndIgnoreColumn() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().entityBuilder()
                .logicDeleteColumnName("deleted") // 基于数据库字段
                .logicDeletePropertyName("deleteFlag")// 基于模型属性
                .addIgnoreColumns("age") // 基于数据库字段
                .build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 自定义模板生成的文件名称
     * result: TSimple -> TSimpleEntity
     */
    @Test
    public void testCustomTemplateName() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig()
                .entityBuilder().formatFileName("%sEntity")
                .mapperBuilder().formatMapperFileName("%sDao").formatXmlFileName("%sXml")
                .controllerBuilder().formatFileName("%sAction")
                .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImp")
                .build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 自定义模板生成的文件路径
     *
     * @see OutputFile
     */
    @Test
    public void testCustomTemplatePath() {
        // 设置自定义路径
        String projectPath = System.getProperty("user.dir");
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, projectPath+ "/src/main/resources/mapper");
        pathInfo.put(OutputFile.mapper, projectPath+ "/src/main/java/com/segroup/hospitalsite/mapper");
        pathInfo.put(OutputFile.entity, projectPath+ "/src/main/java/com/segroup/hospitalsite/entity");
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.packageInfo(packageConfig().pathInfo(pathInfo).build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 自定义模板
     */
    @Test
    public void testCustomTemplate() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.template(templateConfig()
                .entity("/templates/entity1.java")
                .build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 自定义注入属性
     */
    @Test
    public void testCustomMap() {
        // 设置自定义属性
        Map<String, Object> map = new HashMap<>();
        map.put("abc", 123);
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.template(templateConfig()
                .entity("/templates/entity1.java")
                .build());
        generator.injection(injectionConfig().customMap(map).build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    /**
     * 自定义文件
     * key为文件名称，value为文件路径
     * 输出目录为 other
     */
    @Test
    public void testCustomFile() {
        // 设置自定义输出文件
        Map<String, String> customFile = new HashMap<>();
        customFile.put("test.txt", "/templates/test.vm");
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.injection(injectionConfig().customFile(customFile).build());
        generator.global(globalConfig().build());
        generator.execute();
    }
}
