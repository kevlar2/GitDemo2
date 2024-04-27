pipeline {
    agent any
    tools {
        maven "MAVEN3"
        jdk "OracleJDK8"
    }
    stages {
        stage('VALIDATE CODE') {
            steps {
                sh 'mvn validate'
            }
        }

        stage('COMPILE CODE') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('RUN TEST') {
            steps {
                sh 'mvn test'
            }
        }
    }
}