pipeline {
    agent any
    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MySonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Dependency Check') {
            steps {
                sh './dependency-check.sh'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t sample-java-poc .'
            }
        }
        stage('Trivy Scan') {
            steps {
                sh 'trivy image sample-java-poc'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 sample-java-poc'
            }
        }
    }
}
