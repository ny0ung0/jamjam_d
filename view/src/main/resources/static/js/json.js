//업종1을 선택하면 업종2를 바꿔주는 메서드
function sector1SelectChanged(callback = null) {
	let sector1 = document.querySelector("#sector1").value;
	
	 document.getElementById("sector2").innerHTML ='<option value="" class="always">선택</option>';
   
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
   	let sector2 =JSON.parse(this.responseText);
   	
   	sector2.forEach(sector => {
   		 document.getElementById("sector2").innerHTML += '<option value ="'+sector+'">'+sector+'</option>';
   	});
      if (callback) {
		console.log("Sector1 callback called");
            callback();
        };
   };
   xhttp.open("get", "http://localhost:8888/json/sector/" + sector1, true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}
//주소1을 선택하면 주소2내용을 바꿔주는 메서드

function address1SelectChanged(callback = null) {
    let address1 = document.querySelector("#address1").value;
    document.getElementById("address2").innerHTML = '<option value="" class="always">선택</option>';

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let address2 = JSON.parse(this.responseText);
        address2.forEach(address => {
            document.getElementById("address2").innerHTML += '<option value="'+ address +'">' + address + '</option>';
        });

        if (callback) {
            callback();
        };
    };
    xhttp.open("get", "http://localhost:8888/json/address2/" + address1, true);
    xhttp.send();
}
//직무1을 선택하면 직무2의 내용을 바꿔주는 메서드
function job_role1SelectChanged(callback = null) {
	let job_role1 = document.querySelector("#job_role1").value;
	 document.getElementById("job_role2").innerHTML ='<option value="" class="always">선택</option>';
	 document.getElementById("keyword1").innerHTML ='';
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
    	let job_role2 =JSON.parse(this.responseText);
    	
    	job_role2.forEach(role => {
    		
    		
    		 document.getElementById("job_role2").innerHTML += '<option value ="' +role+'">'+role+'</option>';
    		 
    	});
      if (callback) {
            callback();
        }; 
    };
    xhttp.open("get", "http://localhost:8888/json/job/" + job_role1, true);
   // xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

//스킬1을 선택하면 스킬2내용을 바꿔주는 메서드
function required_skill1SelectChanged(callback = null) {
	let required_skill1 = document.querySelector("#required_skill1").value;
	//alert(sector1);
	 document.getElementById("required_skill2").innerHTML ='<option value="" class="always">선택</option>';
	 document.getElementById("keyword2").innerHTML ='';
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
   	let required_skill2 =JSON.parse(this.responseText);
   	
   	required_skill2.forEach(required_skill => {
   		 document.getElementById("required_skill2").innerHTML += '<option value ="' +required_skill+'">'+required_skill+'</option>';
   	});
     if (callback) {
            callback();
        }; 
   };
   xhttp.open("get", "http://localhost:8888/json/required_skill2/" + required_skill1, true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}


//페이지 오픈할때 첫번째 셀렉트에 데이터 넣는 메서드
function pre1(callback = null){
	//직무 관련
	let job_role1 =document.getElementById("job_role1");
	job_role1.innerHTML ='<option value="" class="always">선택</option>';
	document.getElementById("job_role2").innerHTML ='<option value="" class="always">선택</option>';
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
	  
   	let results =JSON.parse(this.responseText);
   	//alert(results);
   	results.forEach(result => {
   		//아직 미완성 
   		//alert(result);
   		let code =result.코드;
   		let role = result.상위직무;
   		job_role1.innerHTML += '<option value =' +code+'>'+role+'</option>';
   		
   	});
     if (callback) {
            callback();
        }; 
   };
   xhttp.open("get", "http://localhost:8888/json/job_category", true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}
function  pre2(callback = null){
   //고용형태 관련
   let employment_type = document.getElementById("employment_type");
   employment_type.innerHTML ='<option value="" class="always">선택</option>';
   
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
	  
   	let results =JSON.parse(this.responseText);
   	//alert(results);
   	results.forEach(result => {
   		//아직 미완성 
   		//alert(result);
   		//let code =result.코드;
   		let text = result.근무형태;
   		employment_type.innerHTML += '<option value =' +text+'>'+text+'</option>';
   	});
   	  if (callback) {
            callback();
        };
      
   };
   xhttp.open("get", "http://localhost:8888/json/workType", true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
   
}
function  pre3(callback = null){
	   //학력관련
	   let education_requirement = document.getElementById("education_requirement");
	   education_requirement.innerHTML ='<option value="" class="always">선택</option>';
	   
	   const xhttp = new XMLHttpRequest();
	   xhttp.onload = function() {
		  
	   	let results =JSON.parse(this.responseText);
	   	//alert(results);
	   	results.forEach(result => {
	   		//아직 미완성 
	   		//alert(result);
	   		//let code =result.코드;
	   		let text = result.학력;
	   		education_requirement.innerHTML += '<option value =' +text+'>'+text+'</option>';
	   		
	   	});
	   	  if (callback) {
            callback();
        }; 
	   	
	   };
	   xhttp.open("get", "http://localhost:8888/json/eduCode", true);
	  // xhttp.setRequestHeader("Content-type", "application/json");
	   xhttp.send();
	   
	}
function pre4(callback = null){
	//업종 관련
	let sector1 =document.getElementById("sector1");
	sector1.innerHTML ='<option value="" class="always">선택</option>';
	document.getElementById("sector2").innerHTML ='<option value="" class="always">선택</option>';
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
	  
   	let results =JSON.parse(this.responseText);
   	//alert(results);
   	results.forEach(result => {
   		//아직 미완성 
   		//alert(result);
   		let code =result.코드;
   		let name = result.업종명;
   		sector1.innerHTML += '<option value =' +code+'>'+name+'</option>';
   		
   	});
   	  if (callback) {
            callback();
        }; 
    
   };
   xhttp.open("get", "http://localhost:8888/json/sector_category", true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}
function pre5(callback = null){
	//주소관련
	let address1 =document.getElementById("address1");
	address1.innerHTML ='<option value="" class="always">선택</option>';
	document.getElementById("address2").innerHTML ='<option value="" class="always">선택</option>';
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
	  
   	let result =JSON.parse(this.responseText);
    Object.keys(result).forEach(key => {
        address1.innerHTML += '<option value="' + key + '">' + key + '</option>';
    });
        if (callback) {
            callback();
        };
   };
   xhttp.open("get", "http://localhost:8888/json/address1", true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}
function pre6(callback = null){
	//필요스킬 관련
	let required_skill1 =document.getElementById("required_skill1");
	required_skill1.innerHTML ='<option value="" class="always">선택</option>';
	document.getElementById("required_skill2").innerHTML ='<option value="" class="always">선택</option>';
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
	  
   	let result =JSON.parse(this.responseText);
    Object.keys(result).forEach(key => {
    	required_skill1.innerHTML += '<option value="' + key + '">' + key + '</option>';
    });
        if (callback) {
            callback();
        }; 
   };
   xhttp.open("get", "http://localhost:8888/json/required_skill1", true);
  // xhttp.setRequestHeader("Content-type", "application/json");
   xhttp.send();
}
// 정보 수정 페이지에서 필요한 셀렉트 기본 값 지정 함수
function selectOptionByValue(selectId, valueToSelect) {
        // 셀렉트 요소를 찾기
        let selectElement = document.getElementById(selectId);
        
        // 옵션들 중에 주어진 값을 가진 옵션을 찾기
        for (let i = 0; i < selectElement.options.length; i++) {
            if (selectElement.options[i].value === valueToSelect) {
                // 해당 값을 가진 옵션을 선택
                selectElement.selectedIndex = i;
                break;
            }
        }
    }
    