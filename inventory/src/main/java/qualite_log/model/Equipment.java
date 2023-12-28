    package qualite_log.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEquipment", scope=Equipment.class)
    public class Equipment {
        private static Long nextId = 1L;
        private Long idEquipment;

        @JsonBackReference
        private EquipmentType type;

        private String reference;
        private String version;

        public EquipmentType getType() {
            return type;
        }

        public void setType(EquipmentType type) {
            this.type = type;

            //type.addEquipments(this);
        }

        public Long NextgetId() {
            return nextId;
        }

        public Long getIdEquipment() {
            return idEquipment;
        }

        private void setIdEquipment() {
            idEquipment = nextId++;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Equipment() {
            setIdEquipment();
            reference = "XXXXX";
        }

        public Equipment(EquipmentType type) {
            setIdEquipment();
            reference = "XXXXX";
            setType(type);
        }

        public Equipment(String reference, String version) {
            setIdEquipment();
            this.reference = reference;
            this.version = version;
        }

        public Equipment(String reference, String version, EquipmentType type) {
            setIdEquipment();
            this.reference = reference;
            this.version = version;
            setType(type);
        }
        
        public String toString() {
            return type.toString() + "-" + reference;
        }
    }
