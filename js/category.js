function traerInformacionCategorias(){
    $.ajax({
        url:"http://129.151.109.134:8080/api/Category/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuestaCategorias(respuesta);
        }
    });
}

function pintarRespuestaCategorias(items){
    let myTable="<table>";
    for(f=0;f<items.length;f++){
        myTable+="<tr>";
        myTable+="<td>" +items[f].name+"</td>";
        myTable+="<td>" +items[f].description+"</td>";
        myTable+="<td> <button onclick='actualizarInformacionCategory("+items[f].id+")'>Actualizar</button> </td>";
        myTable+="<td> <button onclick='borrarElementoCategory("+items[f].id+")'>Borrar</button> </td>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultadoCategory").html(myTable);
}

function guardarInformacionCategorias(){
    let myData={
        name:$("#Cname").val(),
        description:$("#Cdescription").val()
    };

    $.ajax({
        url: "http://129.151.109.134:8080/api/Category/save",
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data:myData,
        data: JSON.stringify(myData),
        datatype:"JSON",

        success:function(respuesta){
            $("#resultadoCategory").empty();
            $("#Cname").val("");
            $("#Cdescription").val("");
            traerInformacionCategorias();
            alert("se ha guardado el dato");
        }
    });
}

function actualizarInformacionCategory(idElemento){
    let myData={
        id:idElemento,
        name:$("#Cname").val(),
        description:$("#Cdescription").val()

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.151.109.134:8080/api/Category/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",

        success:function(respuesta){
            $("#resultadoCategory").empty();
            $("#Cname").val("");
            $("#Cdescription").val("");
            traerInformacionCategorias();
            alert("se ha guardado el dato");
        }
    });

}

function borrarElementoCategory(idElemento){

    $.ajax({
        url: "http://129.151.109.134:8080/api/Category/"+idElemento,
        type:"DELETE",
        contentType:"application/JSON",
        datatype:"JSON",

        success:function(respuesta){
            $("#resultadoCategory").empty();
            $("#Cname").val("");
            $("#Cdescription").val("");
            traerInformacionCategorias();
            alert("se ha guardado el dato");
        }

    });
}