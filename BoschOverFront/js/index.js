document.getElementById("logar").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    
    const edv = document.getElementById("edv").value;
    const password = document.getElementById("pass").value;

    
    const data = {
      edv: edv,
      password: password
    };

    
    try {
      const response = await fetch("http://localhost:8080/auth", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        console.log("Login efetuado com sucesso!");
        window.location.href = "inicio.html"
      } else {
        console.log("Erro ao logar:", response.status);
      }
    } catch (error) {
      console.error("Erro de requisição:", error);
    }
  });