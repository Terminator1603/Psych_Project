var names = ['yash','rohit','akis'];
var i=0;
var mydiv;
var odiv = document.querySelector("div.container");
for(i=0;i<names.length;i++)
{
  mydiv= document.createElement('DIV');
  if(i===0)
  mydiv.className=('grid-item-leader');
  else
  mydiv.className=('grid-item');
  mydiv.innerHTML = names[i];
  odiv.appendChild(mydiv);
}
