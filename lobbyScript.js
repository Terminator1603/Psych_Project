var names = ["Yash", "Rohit", "Akis"];
var i = 0;
var mydiv;
var odiv = document.querySelector("div.container--lobby");
for (i = 0; i < names.length; i++) {
  mydiv = document.createElement("DIV");
  if (i === 0) mydiv.className = "grid_item grid_item--leader";
  else mydiv.className = "grid_item";
  mydiv.innerHTML = names[i];
  odiv.appendChild(mydiv);
}
