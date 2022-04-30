pipeline {
    agent any
    triggers {
            pollSCM '* * * * *'
        }
    tools {
        maven 'M3'
      }
      stages {
       stage('init') {
          checkout scm
       }
       stage('build') {
          sh '''
             mvn clean package
          '''
       }
       stage('deploy') {

       }
    }
}