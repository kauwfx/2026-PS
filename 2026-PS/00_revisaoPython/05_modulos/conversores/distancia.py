def km_para_milhas(km):
    """Converte quilômetros para milhas."""
    return km * 0.621371

def milhas_para_km(milhas):
    """Converte milhas para quilômetros."""
    return milhas / 0.621371

def metros_para_pes(metros):
    """Converte metros para pés."""
    return metros * 3.28084

if __name__ == "__main__":
    print("Testando distancia.py...")
    print(f"100 km = {km_para_milhas(100):.2f} milhas")
    print(f"62 milhas = {milhas_para_km(62):.2f} km")
    print(f"10 metros = {metros_para_pes(10):.2f} pés")
    print("OK!")