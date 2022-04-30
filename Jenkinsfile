pipeline {
    agent any
    triggers {
            pollSCM '* * * * *'
        }
    tools {
            maven 'M3'
        }
        stages {
            stage ('Initialize') {
                steps {
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }

            stage ('Build') {
                steps {
                    sh 'mvn clean install'
                }
                post {
                    success {
                        junit 'target/surefire-reports/**/*.xml'
                    }
                }
            }
            stage('Deploy') {
                steps {
                  dir("${WORKSPACE}"){
                          sh '''
                              git push https://git.heroku.com/book-store-maven.git HEAD:master
                              '''
                      }
                }
            }
        }
}