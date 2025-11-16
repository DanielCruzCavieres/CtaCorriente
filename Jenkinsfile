pipeline {
    agent any

    tools {
        maven 'Maven_3.9.x'
        jdk   'JDK11'
    }

    stages {

        stage('Etapa 1 - Preparación / Checkout') {
            steps {
                echo 'Clonando proyecto desde GitHub...'
                git branch: 'main',
                    url: 'https://github.com/DanielCruzCavieres/CtaCorriente.git'
            }
        }

        stage('Etapa 2 - Construcción del proyecto') {
            steps {
                echo 'Compilando proyecto y construyendo WAR...'
                bat 'mvn -B clean package -DskipTests'
            }
        }

        stage('Etapa 3 - Pruebas automatizadas') {
            steps {
                echo 'Ejecutando pruebas JUnit + Cucumber...'
                bat 'mvn -B test'
            }
            post {
                always {
                    echo 'Publicando resultados de pruebas...'
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo 'Archivando artefacto WAR...'
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
    }
}
