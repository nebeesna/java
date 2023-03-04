public class Teacher extends Person{
        private String position;
        private int experience;
        public Teacher(){
            super();
            this.position = "No Data";
            this.experience = 0;
        }
        public Teacher(String name, int age, String sex, String position, int experience){
            super(name, age, sex);
            this.position = position;
            this.experience = experience;
        }

        public Teacher(Teacher other){
            super(other.firstName, other.age, other.sex);
            this.position = other.position;
            this.experience = other.experience;
        }

        public int getExperience() {
            return experience;
        }
        public String getPosition() { return position; }

        @Override
        public String toString() {
            return String.format("%s\t%s\t%s\t%s\t%s\t%s", getClass().getName(), firstName,age, sex, position, experience);
        }
}
