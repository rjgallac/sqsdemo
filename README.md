

### manually create infra on aws
create VPC

create instance
 - make sure to assign public IP to ssh to it

create SQS
 - sample-queue

ssh -i ~/robs.pem ec2-34-251-47-183.eu-west-1.compute.amazonaws.com
scp -v -i ~/robs.pem demo-0.0.1-SNAPSHOT.jar ec2-user@ec2-34-244-70-127.eu-west-1.compute.amazonaws.com:~/.

sudo yum install java-17-amazon-corretto

### use terraform

https://medium.com/@hmalgewatta/setting-up-an-aws-ec2-instance-with-ssh-access-using-terraform-c336c812322f

terraform init

terraform apply

terraform destroy
