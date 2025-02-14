resource "aws_security_group" "ms_sg" {
    vpc_id = aws_vpc.ms_vpc.id
    ingress {
        from_port = 22
        to_port = 22
        protocol = "tcp"
        cidr_blocks = [var.my_ip]
    }
    egress {
        from_port = 0
        to_port = 0
        protocol = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress{
        from_port = 9090
        to_port= 9090
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
     ingress{
        from_port = 9091
        to_port= 9091
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
     ingress{
        from_port = 9092
        to_port= 9092
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
}