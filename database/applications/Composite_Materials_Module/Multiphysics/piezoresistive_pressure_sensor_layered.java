/*
 * piezoresistive_pressure_sensor_layered.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:34 by COMSOL 6.3.0.290. */
public class piezoresistive_pressure_sensor_layered {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");
    model.component("comp1").physics("ecis").create("pzrs1", "PiezoresistiveShell");
    model.component("comp1").physics("ecis").feature("pzrs1").selection().all();

    model.component("comp1").multiphysics().create("pzrl1", "PiezoresistivityLS", 2);
    model.component("comp1").multiphysics("pzrl1").set("Solid_physics", "lshell");
    model.component("comp1").multiphysics("pzrl1").set("ElectricCurrents_physics", "ecis");
    model.component("comp1").multiphysics("pzrl1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pzrl1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1")
         .insertFile("piezoresistive_pressure_sensor_shell_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").feature().remove("unisel1");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .add("ige2", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.component("comp1").geom("geom1").feature().move("sel3", 10);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").feature().remove("adjsel1");
    model.component("comp1").geom("geom1").runPre("difsel1");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"sel3"});
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"-45[deg]", "0", "0"});
    model.component("comp1").coordSystem("sys1").set("mastersystem", "sys2");
    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.material("mat1").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic, Voigt notation");
    model.material("mat1").propertyGroup()
         .create("PiezoresistanceForm", "PiezoresistanceForm", "Piezoresistance form");
    model.material("mat1").propertyGroup("PiezoresistanceForm").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup()
         .create("ElastoresistanceForm", "ElastoresistanceForm", "Elastoresistance form");
    model.material("mat1").propertyGroup("ElastoresistanceForm").func().create("an1", "Analytic");
    model.material("mat1").label("n-Silicon (single-crystal, lightly doped)");
    model.material("mat1")
         .comments("C. S. Smith, \u201cPiezoresistance Effect in Silicon and Germanium\u201d, Physical Review, vol. 94, no. 1, pp. 42-49, 1957.\n\nC. Jacoboni, C. Canali, G. Ottaviani and A. Alberigi Quaranta, \u201cA Review of some Charge Transport Properties of Silicon\u201d, Solid-State Electronics, vol. 20, pp. 77-89, 1977.");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "sigma0");
    model.material("mat1").propertyGroup("def").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"N"});
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{""});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat1").propertyGroup("def").func("an1").set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat1").propertyGroup("def").set("density", "2330[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])"});
    model.material("mat1").propertyGroup("def").addInput("numberdensity");
    model.material("mat1").propertyGroup("Anisotropic").label("Anisotropic");
    model.material("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.material("mat1").propertyGroup("AnisotropicVoGrp").label("Anisotropic, Voigt notation");
    model.material("mat1").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.material("mat1").propertyGroup("PiezoresistanceForm").label("Piezoresistance form");
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").set("funcname", "sigma0");
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").set("args", new String[]{"N"});
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").set("argunit", new String[]{""});
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat1").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat1").propertyGroup("PiezoresistanceForm")
         .set("Pil", new String[]{"-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "53.4e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-102.2e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-13.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]"});
    model.material("mat1").propertyGroup("PiezoresistanceForm").addInput("numberdensity");
    model.material("mat1").propertyGroup("ElastoresistanceForm").label("Elastoresistance form");
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").set("funcname", "sigma0");
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.1400/sqrt(1+N/(N/350 +3e22)))");
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").set("args", new String[]{"N"});
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").set("argunit", new String[]{""});
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat1").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat1").propertyGroup("ElastoresistanceForm")
         .set("ml", new String[]{"-101.4/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "57.6/sigma0(nd[m^3])[S/m]", "-101.4/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "57.6/sigma0(nd[m^3])[S/m]", "57.6/sigma0(nd[m^3])[S/m]", "-101.4/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "-10.8/sigma0(nd[m^3])[S/m]"});
    model.material("mat1").propertyGroup("ElastoresistanceForm").addInput("numberdensity");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat2").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.material("mat2").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic, Voigt notation");
    model.material("mat2").propertyGroup()
         .create("PiezoresistanceForm", "PiezoresistanceForm", "Piezoresistance form");
    model.material("mat2").propertyGroup("PiezoresistanceForm").func().create("an1", "Analytic");
    model.material("mat2").propertyGroup()
         .create("ElastoresistanceForm", "ElastoresistanceForm", "Elastoresistance form");
    model.material("mat2").propertyGroup("ElastoresistanceForm").func().create("an1", "Analytic");
    model.material("mat2").label("p-Silicon (single-crystal, lightly doped)");
    model.material("mat2")
         .comments("C. S. Smith, \u201cPiezoresistance Effect in Silicon and Germanium\u201d, Physical Review, vol. 94, no. 1, pp. 42-49, 1957.\n\nC. Jacoboni, C. Canali, G. Ottaviani and A. Alberigi Quaranta, \u201cA Review of some Charge Transport Properties of Silicon\u201d, Solid-State Electronics, vol. 20, pp. 77-89, 1977.");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat2").propertyGroup("def").func("an1").set("funcname", "sigma0");
    model.material("mat2").propertyGroup("def").func("an1").set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"N"});
    model.material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{""});
    model.material("mat2").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat2").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat2").propertyGroup("def").func("an1").set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat2").propertyGroup("def").set("density", "2330[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])", "0", "0", "0", "sigma0(nd[m^3])"});
    model.material("mat2").propertyGroup("def").addInput("numberdensity");
    model.material("mat2").propertyGroup("Anisotropic").label("Anisotropic");
    model.material("mat2").propertyGroup("Anisotropic")
         .set("D", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.material("mat2").propertyGroup("AnisotropicVoGrp").label("Anisotropic, Voigt notation");
    model.material("mat2").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.material("mat2").propertyGroup("PiezoresistanceForm").label("Piezoresistance form");
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").label("Analytic 1");
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").set("funcname", "sigma0");
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").set("args", new String[]{"N"});
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").set("argunit", new String[]{""});
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat2").propertyGroup("PiezoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat2").propertyGroup("PiezoresistanceForm")
         .set("Pil", new String[]{"6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "-1.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "6.6e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]", "0[\u03a9*m/Pa]", 
         "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "0[\u03a9*m/Pa]", "138.1e-11[1/Pa]/sigma0(nd[m^3])[S/m]"});
    model.material("mat2").propertyGroup("PiezoresistanceForm").addInput("numberdensity");
    model.material("mat2").propertyGroup("ElastoresistanceForm").label("Elastoresistance form");
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").label("Analytic 1");
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").set("funcname", "sigma0");
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("expr", "(N*e_const*0.048/sqrt(1+N/(N/81 +4e22)))");
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").set("args", new String[]{"N"});
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").set("argunit", new String[]{""});
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").set("plotaxis", new String[]{"off"});
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1").set("plotfixedvalue", new String[]{""});
    model.material("mat2").propertyGroup("ElastoresistanceForm").func("an1")
         .set("plotargs", new String[][]{{"N", "", ""}});
    model.material("mat2").propertyGroup("ElastoresistanceForm")
         .set("ml", new String[]{"9.6/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "1.8/sigma0(nd[m^3])[S/m]", "9.6/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "1.8/sigma0(nd[m^3])[S/m]", "1.8/sigma0(nd[m^3])[S/m]", "9.6/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]", "0[\u03a9*m]", 
         "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "0[\u03a9*m]", "110.0/sigma0(nd[m^3])[S/m]"});
    model.material("mat2").propertyGroup("ElastoresistanceForm").addInput("numberdensity");
    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").feature().remove("stllmat1");
    model.component("comp1").material("stlmat1").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat1").feature().create("lmat1", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("thickness", "20[um]", 0);
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("meshPoints", 1, 0);
    model.component("comp1").material("stlmat1").feature().create("lmat2", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat2").selection().named("geom1_unisel2");
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("link", "mat2", 0);
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("thickness", "400[nm]", 0);
    model.component("comp1").material("stlmat1").feature("lmat2").setIndex("meshPoints", 1, 0);

    model.view().create("view6", 2);
    model.view("view6").set("showgrid", false);
    model.view("view6").axis().set("viewscaletype", "manual");
    model.view("view6").axis().set("yscale", 9803.921568627453);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("view", "view6");
    model.result("pg1").set("ispendingzoom", true);
    model.result("pg1").set("edges", false);
    model.result("pg1").label("\u5c42\u6a2a\u622a\u9762\u9884\u89c8");
    model.result("pg1").create("arrow1", "ArrowData");
    model.result("pg1").feature("arrow1").set("pointdata", new double[][]{{-0.02}, {0}});
    model.result("pg1").feature("arrow1").set("vectordata", new double[][]{{0}, {5.0999999999999995E-6}});
    model.result("pg1").feature("arrow1").set("coloring", "uniform");
    model.result("pg1").feature("arrow1").set("color", "custom");
    model.result("pg1").feature("arrow1").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").feature("arrow1").set("autoscale", 1.0199999999999999E-4);
    model.result("pg1").create("surf1", "SurfaceData");
    model.result("pg1").feature("surf1")
         .set("pointdata", new double[][]{{0, 0.46499999999999997, 0, 0.46499999999999997}, {0, 0, 1.9999999999999998E-5, 1.9999999999999998E-5}});
    model.result("pg1").feature("surf1").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf1").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 1);
    model.result("pg1").feature("surf1").set("rangecolormax", 2);
    model.result("pg1").feature("surf1").set("rangedataactive", true);
    model.result("pg1").feature("surf1").set("rangedatamin", 1);
    model.result("pg1").feature("surf1").set("rangedatamax", 2);
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf1").set("colortablerev", true);
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").create("line1", "LineData");
    model.result("pg1").feature("line1")
         .set("pointdata", new double[][]{{0, 0.01, 0.46499999999999997, 0.475, 0, 0.01, 0.46499999999999997, 0.475}, {0, 0, 0, 0, 1.9999999999999998E-5, 1.9999999999999998E-5, 1.9999999999999998E-5, 1.9999999999999998E-5}});
    model.result("pg1").feature("line1").set("elementdata", new int[][]{{0, 4, 4, 6}, {2, 6, 0, 2}});
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").create("tann_uppermiddle", "TableAnnotation");
    model.result("pg1").feature("tann_uppermiddle").set("source", "localtable");
    model.result("pg1").feature("tann_uppermiddle").set("showpoint", false);
    model.result("pg1").feature("tann_uppermiddle").set("showframe", false);
    model.result("pg1").feature("tann_uppermiddle").set("anchorpoint", "uppermiddle");
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.2375, 0, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -1.0199999999999998E-6, 0, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c1\u201d:}}\\newline \\unicode{\u591a\u5c42\u6750\u6599 1}", 0, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf2", "SurfaceData");
    model.result("pg1").feature("surf2")
         .set("pointdata", new double[][]{{0.5, 0.965, 0.5, 0.965}, {0, 0, 1.9999999999999998E-5, 1.9999999999999998E-5}});
    model.result("pg1").feature("surf2").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf2").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", 1);
    model.result("pg1").feature("surf2").set("rangecolormax", 2);
    model.result("pg1").feature("surf2").set("rangedataactive", true);
    model.result("pg1").feature("surf2").set("rangedatamin", 1);
    model.result("pg1").feature("surf2").set("rangedatamax", 2);
    model.result("pg1").feature("surf2").set("coloring", "colortable");
    model.result("pg1").feature("surf2").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf2").set("colortablerev", true);
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").create("surf3", "SurfaceData");
    model.result("pg1").feature("surf3")
         .set("pointdata", new double[][]{{0.51, 0.975, 0.51, 0.975}, {1.9999999999999998E-5, 1.9999999999999998E-5, 2.0399999999999998E-5, 2.0399999999999998E-5}});
    model.result("pg1").feature("surf3").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf3").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf3").set("rangecoloractive", true);
    model.result("pg1").feature("surf3").set("rangecolormin", 1);
    model.result("pg1").feature("surf3").set("rangecolormax", 2);
    model.result("pg1").feature("surf3").set("rangedataactive", true);
    model.result("pg1").feature("surf3").set("rangedatamin", 1);
    model.result("pg1").feature("surf3").set("rangedatamax", 2);
    model.result("pg1").feature("surf3").set("coloring", "colortable");
    model.result("pg1").feature("surf3").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf3").set("colortablerev", true);
    model.result("pg1").feature("surf3").set("colorlegend", false);
    model.result("pg1").create("line2", "LineData");
    model.result("pg1").feature("line2")
         .set("pointdata", new double[][]{{0.5, 0.51, 0.965, 0.975, 0.5, 0.51, 0.965, 0.975, 0.5, 0.51, 0.965, 0.975}, {0, 0, 0, 0, 1.9999999999999998E-5, 1.9999999999999998E-5, 1.9999999999999998E-5, 1.9999999999999998E-5, 2.0399999999999998E-5, 2.0399999999999998E-5, 2.0399999999999998E-5, 2.0399999999999998E-5}});
    model.result("pg1").feature("line2")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 9, 9, 11}, {2, 7, 0, 2, 11, 5, 7}});
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.7375, 1, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -1.0199999999999998E-6, 1, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c2\u201d:}}\\newline \\unicode{\u591a\u5c42\u6750\u6599 1}\\newline \\unicode{\u591a\u5c42\u6750\u6599 2}", 1, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("line3", "LineData");
    model.result("pg1").feature("line3")
         .set("pointdata", new double[][]{{-0.01, 0.0298, -0.01, 0.0298, 0.03975, 0.07955000000000001, 0.03975, 0.07955000000000001, 0.08950000000000001, 0.12930000000000003, 0.08950000000000001, 0.12930000000000003, 0.13924999999999998, 0.17905, 0.13924999999999998, 0.17905, 0.189, 0.2288, 0.189, 0.2288, 0.23875000000000002, 0.27855, 0.23875000000000002, 0.27855, 0.2885, 0.3283, 0.2885, 0.3283, 0.33825, 0.37805, 0.33825, 0.37805, 0.388, 0.4278, 0.388, 0.4278, 0.43775000000000003, 0.47755000000000003, 0.43775000000000003, 0.47755000000000003, 0.48750000000000004, 0.5273000000000001, 0.48750000000000004, 0.5273000000000001, 0.53725, 0.5770500000000001, 0.53725, 0.5770500000000001, 0.587, 0.6268, 0.587, 0.6268, 0.63675, 0.67655, 0.63675, 0.67655, 0.6865, 0.7263, 0.6865, 0.7263, 0.7362500000000001, 0.7760500000000001, 0.7362500000000001, 0.7760500000000001, 0.786, 0.8258000000000001, 0.786, 0.8258000000000001, 0.83575, 0.87555, 0.83575, 0.87555, 0.8855000000000001, 0.9253, 0.8855000000000001, 0.9253, 0.93525, 0.97505, 0.93525, 0.97505}, {-1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7, -1.0199999999999999E-7, -1.0199999999999999E-7, 1.0199999999999999E-7, 1.0199999999999999E-7}});
    model.result("pg1").feature("line3")
         .set("elementdata", new int[][]{{0, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16, 17, 19, 18, 20, 21, 23, 22, 24, 25, 27, 26, 28, 29, 31, 30, 32, 33, 35, 34, 36, 37, 39, 38, 40, 41, 43, 42, 44, 45, 47, 46, 48, 49, 51, 50, 52, 53, 55, 54, 56, 57, 59, 58, 60, 61, 63, 62, 64, 65, 67, 66, 68, 69, 71, 70, 72, 73, 75, 74, 76, 77, 79, 78}, {1, 3, 2, 0, 5, 7, 6, 4, 9, 11, 10, 8, 13, 15, 14, 12, 17, 19, 18, 16, 21, 23, 22, 20, 25, 27, 26, 24, 29, 31, 30, 28, 33, 35, 34, 32, 37, 39, 38, 36, 41, 43, 42, 40, 45, 47, 46, 44, 49, 51, 50, 48, 53, 55, 54, 52, 57, 59, 58, 56, 61, 63, 62, 60, 65, 67, 66, 64, 69, 71, 70, 68, 73, 75, 74, 72, 77, 79, 78, 76}});
    model.result("pg1").feature("line3").label("\u5c42\u4e2d\u9762");
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "custom");
    model.result("pg1").feature("line3").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").set("ylabel", "\u539a\u5ea6\u5750\u6807");
    model.result("pg1").set("defaultaxisunits", new String[]{"", "m"});
    model.result("pg1").run();

    model.component("comp1").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("lshell").feature("lemm1").set("AnisotropicOption", "AnisotropicVo");
    model.component("comp1").physics("lshell").feature().duplicate("lemm2", "lemm1");
    model.component("comp1").physics("lshell").feature("lemm2").selection().named("geom1_sel1");
    model.component("comp1").physics("lshell").feature("lemm2").set("allLayers", false);
    model.component("comp1").physics("lshell").feature("lemm2").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("lshell").create("fixi1", "FixedIntS", 2);
    model.component("comp1").physics("lshell").feature("fixi1").selection().named("geom1_difsel1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("lshell").feature("fixi1").set("applyTo", "bottom");
    model.component("comp1").physics("lshell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("lshell").feature("fl1").set("applyTo", "top");
    model.component("comp1").physics("lshell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("lshell").feature("fl1").set("pressure", "100[kPa]");
    model.component("comp1").physics("lshell").feature("fl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("lshell").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("ecis").selection().named("geom1_unisel2");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("allLayers", false);
    model.component("comp1").physics("ecis").prop("LayerSelection").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").feature("csh1").set("minput_numberdensity", "1.45e20[1/cm^3]");
    model.component("comp1").physics("ecis").feature("pzrs1").selection().named("geom1_sel1");
    model.component("comp1").physics("ecis").feature("pzrs1").set("minput_numberdensity", "1.32e19[1/cm^3]");
    model.component("comp1").physics("ecis").feature("csh1").create("bterm1", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm1").selection().set(11, 13);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm1").set("V0", 3);
    model.component("comp1").physics("ecis").feature("csh1").create("bterm2", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm2").selection().set(7);
    model.component("comp1").physics("ecis").feature("csh1").create("bterm3", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm3").selection().set(72, 73);
    model.component("comp1").physics("ecis").feature("csh1").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bgnd1").selection().set(70);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 60);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.5);
    model.component("comp1").mesh("mesh1").feature().duplicate("size1", "size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 6);
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection()
         .set(27, 29, 38, 39, 40, 41, 42, 43, 44, 51, 52, 53);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.4);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 2);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 3);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1lshelllshl");
    model.result("pg2").label("\u5e94\u529b (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ecis)");
    model.result("pg3").set("data", "dset1lshelllshl");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();

    model.view("view7").set("showgrid", false);

    model.result().dataset().create("dset1lshelllshl1", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl1").set("data", "dset1");
    model.result().dataset("dset1lshelllshl1")
         .set("defaultPlotIDs", new String[]{"stress|lshell", "displacement|lshell"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1lshelllshl1");
    model.result("pg4").label("\u4f4d\u79fb (lshell)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"lshell.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg4").label("\u4f4d\u79fb (lshell)");
    model.result("pg4").run();
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset1lshelllshl");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "lshell.slGp12");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(6, 76);
    model.result("pg6").feature("lngr1").set("expr", "side(2,lshell.atxd1(lshell.d,lshell.slGp12))");
    model.result("pg6").feature("lngr1").set("unit", "MPa");
    model.result("pg6").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("geom1_unisel2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("view", "new");
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "V");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("colortable", "ThermalDark");
    model.result("pg7").feature("con1").set("colortabletrans", "reverse");
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"ecis.JsX", "ecis.JsY", "ecis.JsZ"});
    model.result("pg7").feature("arws1")
         .set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6 \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg7").feature("arws1").set("descractive", true);
    model.result("pg7").feature("arws1").set("scaleactive", true);
    model.result("pg7").feature("arws1").set("scale", 0.005);
    model.result("pg7").feature("arws1").set("arrowcount", 3000);
    model.result("pg7").feature("arws1").set("color", "blue");

    model.view("view8").set("showgrid", false);

    model.result("pg7").run();
    model.result().dataset().create("dset1lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset1lshelllshlge").set("data", "dset1");
    model.result().dataset("dset1lshelllshlge").set("scale", "50*0.0831890330807703");
    model.result().dataset("dset1lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1lshelllshlge");
    model.result("pg8").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("coloring", "uniform");
    model.result("pg8").feature("surf1").set("color", "gray");
    model.result("pg8").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "stlmat1.zone");
    model.result("pg9").feature("surf1").set("coloring", "colortable");
    model.result("pg9").feature("surf1").set("colortable", "TrafficLight");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().move("pg9", 1);
    model.result("pg7").run();

    model.title("\u538b\u963b\u5f0f\u538b\u529b\u4f20\u611f\u5668 - \u591a\u5c42\u58f3\u7248\u672c");

    model
         .description("\u672c\u6559\u7a0b\u7ed3\u5408\u4f7f\u7528\u201c\u591a\u5c42\u58f3\u4e2d\u7684\u7535\u6d41\u201d\u4e0e\u201c\u591a\u5c42\u58f3\u201d\u63a5\u53e3\u6765\u6a21\u62df\u538b\u963b\u5f0f\u538b\u529b\u4f20\u611f\u5668\u3002\n\n\u672c\u6559\u7a0b\u57fa\u4e8e\u5bf9\u65e9\u671f MEMS \u538b\u529b\u4f20\u611f\u5668\uff08\u6469\u6258\u7f57\u62c9 MAP \u4f20\u611f\u5668 (S.D. Senturia, Microsystem Design, Springer, 2000)\uff09\u7684\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("piezoresistive_pressure_sensor_layered.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
