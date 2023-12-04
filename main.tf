terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "eu-west-1"
}

resource "aws_instance" "app_server" {
  ami           = "ami-07355fe79b493752d"
  instance_type = "t2.micro"
  key_name      = "robs"
  security_groups = ["${aws_security_group.ingress-all-test.id}"]
  associate_public_ip_address = "true"
  tags = {
    Name = "ExampleAppServerInstance"
  }
  subnet_id = "${aws_subnet.subnet-uno.id}"
}

resource "aws_sqs_queue" "terraform_queue" {
  name = "sample-queue"

  tags = {
    Environment = "production"
  }
}

resource "aws_subnet" "subnet-uno" {
  cidr_block = "${cidrsubnet(aws_vpc.test-env.cidr_block, 3, 1)}"
  vpc_id = "${aws_vpc.test-env.id}"
  availability_zone = "eu-west-1a"
}

resource "aws_security_group" "ingress-all-test" {
    name = "allow-all-sg"
    vpc_id = "${aws_vpc.test-env.id}"
    ingress {
        cidr_blocks = [
          "0.0.0.0/0"
        ]
        from_port = 22
        to_port = 22
        protocol = "tcp"
    }// Terraform removes the default rule
    egress {
        from_port = 0
        to_port = 0
        protocol = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }
}

resource "aws_vpc" "test-env" {
  cidr_block = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support = true
  tags = {
    Name = "test-env"
  }
}
