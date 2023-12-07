package com.phollux.tuhome.config;

import com.phollux.tuhome.TuHomeApplication;
import com.phollux.tuhome.document_type.repos.DocumentTypeRepository;
import com.phollux.tuhome.role.repos.RoleRepository;

import java.nio.charset.StandardCharsets;

import com.phollux.tuhome.user.repos.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest(
        classes = TuHomeApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles("it")
@Sql({"/data/clearAll.sql", "/data/roleData.sql", "/data/documentTypeData.sql", "/data/userData.sql"})
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
public abstract class BaseIT {

    @ServiceConnection
    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16.1");

    static {
        postgreSQLContainer.withReuse(true)
                .start();
    }

    @Autowired
    public MockMvc mockMvc;


    @Autowired
    public DocumentTypeRepository documentTypeRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public UserRepository userRepository;



    @SneakyThrows
    public String readResource(final String resourceName) {
        return StreamUtils.copyToString(getClass().getResourceAsStream(resourceName), StandardCharsets.UTF_8);
    }

    public String bearerToken() {
        return "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkYW5pZWxAY29ib3MuY29tIiwiaXNzIjoiYXBwIiwiaWF0IjoxNzAxOTE4MzAwLCJleHAiOjE3MDE5NjE1MDB9.pFcvygBr_c1IuchNrRTBlzQlrOBuZ2TGorntYTLMzrYyjf7xpcahKCeoSsnxKrYTLDd1D_YWXFH3K1bgXZbwZ2JOLTu4Z-eMxyaOXH5THe0SZBxtMREYzkBrbHsaMzvLmLUi_TnUF6BvNiPdiNu5n9iwBqW7oW915kVsB8ffdyFLEq4ufoFDn1os8YFEj-EGmhBDAA74p-p1bcRiitoyFDs9UsKOImd8CsZfRCbuDqDg8WdmypGM9oL7YYCVt2MSZwX81VJ1QppW2UQ6DN4QHHGJVeUOoFWO56CeqLIEcoAwLI91TDVVK1eFL6Su9dpt5JPCqp6PElnmuPc044NeMg";
    }

}
