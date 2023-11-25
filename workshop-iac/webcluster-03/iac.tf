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

// Creating webserver.#
resource "digitalocean_droplet" "web_server" {
  count      = 2
  image      = "debian-12-x64"
  name       = "web-server.${count.index}"
  region     = "nyc1"
  size       = "s-1vcpu-1gb"
  monitoring = true
  ssh_keys = [
    digitalocean_ssh_key.javadominicano_ssh_key.fingerprint
  ]
}

resource "digitalocean_droplet" "proxy_server" {
  image      = "debian-12-x64"
  name       = "proxy-server"
  region     = "nyc1"
  size       = "s-1vcpu-1gb"
  monitoring = true
  ssh_keys = [
    digitalocean_ssh_key.javadominicano_ssh_key.fingerprint
  ]
}

output "web_server_ip_address" {
  value       = digitalocean_droplet.web_server.*.ipv4_address
  description = "The public IP address of your Droplet application."
}

output "proxy_server_ip_address" {
  value       = digitalocean_droplet.proxy_server.ipv4_address
  description = "The public IP address of your Droplet application."
}