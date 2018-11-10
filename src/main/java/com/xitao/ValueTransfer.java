package com.xitao;

public class ValueTransfer {
     static  class User {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

         @Override
         public String toString() {
             return "User{" +
                     "name='" + name + '\'' +
                     ", age=" + age +
                     '}';
         }
     }

    public static void  swap(User x, User y) {
        User temp = x;
        x=y;
        y=temp;
        System.out.println(x);
        System.out.println(y);
    }

    public static void main(String[] args) {
        User x = new User("aa",12);
        User y = new User("bb",21);
        System.out.println(x);
        System.out.println(y);
        swap(x,y);
        System.out.println(x);
        System.out.println(y);

    }

}
