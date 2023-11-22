# Mobile-Development
## Material Design 3
### 1. Colors
1.1 General

<img title="MD General colors" alt="img colors in MD" src="MD_colors_general1.png">
<img title="MD General colors" alt="img colors in MD" src="MD_colors_general2.png">

- Statesbars:
- -  background : colorPrimaryDark.
- ToolBar:
- - background : colorPrimary.
- - text : textColorPrimary.
- Window :
- - background : windowBackground.
- Navigation bar : navigationBarColor

<img title="MD General colors" alt="img colors in MD" src="MD_colors_accent.png">

Accent color represente (clickable) :
- Text fields and cursors.
- Text selection.
- Progress bars.
- Selection controls, buttons, and sliders.
- Links.

<img title="MD button color" alt="img colors in MD" src="MD_colors_button.png">

1.2 Action button extends (colorAccent) :
- background : colorControlNormal.
- not selected : colorControlNormal.
- selected : colorControlActivated.
- clicked effect : colorControlHighlight.

<img title="MD General colors" alt="img colors in MD" src="MD_colors_toolbar.png">

1.3 Toolbar
- statebar extends all from (general).
- title : textColorPrimary.
- navigation action/flow menu : textColorSecondary


## Android Java
 Lors du lancement de l'application android appel ces 3 fonctions:
1. OnCreate() : Créer l'application.
2. OnStart()  : Charge la premiere activité.
3. OnResume() : rend l'activité visible.

- Lorsqu'ont lance une activité fille :
1. OnPaused()           : mes pause a l'activité parent.
2. OnCreate() - fille   : creer l'activité fille.
3. OnStart()  - fille   : start l'activité fille.
4. OnResume() - fille   : resume l'activité fille.
5. OnStoped()           : stop l'activité parent.
l'activité est mis automatiquement en pause.

- Revient a l'activité parent (finish a l'activité fille) :
1. OnPaused() - fille   : mes pause a l'activité fille.
2. OnStart()            : charge l'activité parent.
3. OnResume()           : rend l'activité visible.
4. OnStoped() - fille   : stop l'activité fille.
5. OnDestroy()- fille  : destroy l'activité fille.

- Lorsqu'ont réduit l'activité c'est la meme chause que l'orsque tu change d'activité:
1. OnPaused()           : mes pause a l'activité.
2. OnStoped()           : stop l'activité.
   
- Réaparaitre l'application mobile:
1. OnStart()            : charge l'activité.
2. OnResume()           : rend l'activité visible.






