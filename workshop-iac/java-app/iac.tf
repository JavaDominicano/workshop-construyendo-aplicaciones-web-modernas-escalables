terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

resource "digitalocean_ssh_key" "javadominicano_ssh_key" {
  name = "javadominicano-ssh-key"
  public_key = file("sshkeys/sshkey.pub")
}

resource "digitalocean_droplet" "app_server" {
  image      = "debian-12-x64"
  name       = "app-server"
  region     = "nyc1"
  size       = "s-1vcpu-1gb"
  monitoring = true
  ssh_keys = [
    digitalocean_ssh_key.javadominicano_ssh_key.fingerprint
  ]
}

output "app_server_ip_address" {
  value       = digitalocean_droplet.app_server.ipv4_address
  description = "The public IP address of your Droplet application."
}