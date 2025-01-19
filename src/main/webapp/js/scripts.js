let navItems = Array.from(document.getElementsByClassName("nav-item"));
let dropdowns = Array.from(document.getElementsByClassName("dropdown"));

for (let i = 0; i <= 1; i++) {
    let item = navItems[i];
    let drop = dropdowns[i];

    item.addEventListener("mouseenter", () => {
        drop.style.display = "flex";
    });

    item.addEventListener("mouseleave", () => {
        drop.style.display = "none";
    });
}