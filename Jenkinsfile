pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build Application'
            }
        }
		stage('Test') {
            steps {
                echo 'Test Application'
            }
        }
		stage('Deploy') {
            steps {
                echo 'Deploy Application'
            }
        }
    }
	post
	{
	    always
		{
		   emailext body: 'Summay', subject: 'Pipeline Status', to: 'surekhavernekar66@gmail.com'
		}
	}
}
