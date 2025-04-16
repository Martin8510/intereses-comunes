package red.social.interesescomunes.role.infrastructure.output.persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import red.social.interesescomunes.security.model.Permission;
import java.util.Set;


@Converter
public class PermissionSetConverter implements AttributeConverter<Set<Permission>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<Permission> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch ( JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializando permisos", e);
        }
    }

    @Override
    public Set<Permission> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Set<Permission>>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializando permisos", e);
        }
    }
}
