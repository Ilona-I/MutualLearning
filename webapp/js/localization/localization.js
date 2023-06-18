let defaultLocale = 'ua';
let translations = {};

document.addEventListener('DOMContentLoaded', () => {
  let lang = localStorage.getItem("lang");
  if(lang === null){
    setLocale(defaultLocale);
  } else {
    setLocale(lang);
  }
});

const switcher = document.getElementById('localization-switcher');

switcher.onchange = (e) => {
  setLocale(e.target.value);

};

const setLocale = async (newLocale) => {
  localStorage.setItem("lang",newLocale);
  document.getElementById("localization-switcher").value = newLocale;
  translations = await fetchTranslations(newLocale);

  translatePage();
};

const fetchTranslations = async (newLocale) => {

  const response = await fetch(`../../lang/${newLocale}.json`);

  if (!response.ok) {

    console.log(`Could not fetch translations for locale ${newLocale}`);

  }

  return await response.json();

};

function translatePage() {

  document.querySelectorAll('[localization-key]').forEach((element) => {

    let key = element.getAttribute('localization-key');

    let translation = translations[key];

    element.innerText = translation;

  });

}