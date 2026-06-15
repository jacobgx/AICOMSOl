/*
 * solid_multilayer_shell_comparison.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class solid_multilayer_shell_comparison {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electric_Currents");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);

    model.param().set("Cu_thickness", "1[mm]");
    model.param().descr("Cu_thickness", "\u94dc\u5c42\u539a\u5ea6\uff08J \u5f62\u4e0b\u8f6e\u5ed3\uff09");
    model.param().set("Al_thickness", "2[mm]");
    model.param().descr("Al_thickness", "\u94dd\u5c42\u539a\u5ea6\uff08Z \u5f62\u4e0a\u8f6e\u5ed3)");
    model.param().set("Air_thickness", "3[mm]");
    model.param().descr("Air_thickness", "\u6c14\u9699\u539a\u5ea6");
    model.param().set("Semi_thickness", "0.1[mm]");
    model.param().descr("Semi_thickness", "\u8584\u534a\u5bfc\u4f53\u5c42\u539a\u5ea6");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("molarmass", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.material("mat1").materialType("nonSolid");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat2").label("Aluminum");
    model.material("mat2").set("family", "aluminum");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat3").label("Copper");
    model.material("mat3").set("family", "copper");
    model.material("mat3").propertyGroup("def").label("Basic");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("\u534a\u5bfc\u4f53\u6750\u6599");
    model.material("mat4").propertyGroup("def").set("electricconductivity", "");
    model.material("mat4").propertyGroup("def").set("relpermittivity", "");
    model.material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"1000"});
    model.material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("Al-Air-Cu \u5806\u53e0");
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "Al", 0);
    model.material("lmat1").setIndex("link", "mat2", 0);
    model.material("lmat1").setIndex("thickness", "Al_thickness", 0);
    model.material("lmat1").setIndex("layername", "Air", 1);
    model.material("lmat1").setIndex("thickness", "Air_thickness", 1);
    model.material("lmat1").setIndex("layername", "Cu", 2);
    model.material("lmat1").setIndex("link", "mat3", 2);
    model.material("lmat1").setIndex("thickness", "Cu_thickness", 2);
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").label("Cu-Semi-Al \u5806\u53e0");
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat1", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat2").setIndex("meshPoints", 2, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat1", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat2").setIndex("meshPoints", 2, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat1", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat2").setIndex("meshPoints", 2, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat1", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat2").setIndex("meshPoints", 2, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "Cu", 0);
    model.material("lmat2").setIndex("link", "mat3", 0);
    model.material("lmat2").setIndex("thickness", "Cu_thickness", 0);
    model.material("lmat2").setIndex("layername", "Semi", 1);
    model.material("lmat2").setIndex("link", "mat4", 1);
    model.material("lmat2").setIndex("thickness", "Semi_thickness", 1);
    model.material("lmat2").setIndex("layername", "Al", 2);
    model.material("lmat2").setIndex("link", "mat2", 2);
    model.material("lmat2").setIndex("thickness", "Al_thickness", 2);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.15", "Cu_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", 0.05, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"0.05", "Air_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "Cu_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"0.05", "Al_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"0", "Cu_thickness+Air_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("size", "Al_thickness", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .setIndex("size", "0.055-Cu_thickness-Air_thickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("pos", 0.05, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").setIndex("size", "Cu_thickness", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .setIndex("size", "0.055-2*Cu_thickness-Al_thickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .setIndex("pos", "0.15-Cu_thickness", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").setIndex("pos", "Cu_thickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r6", "r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .setIndex("size", "0.1-Al_thickness", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").setIndex("size", "Al_thickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .setIndex("pos", "0.05+Al_thickness", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .setIndex("pos", "0.055-Al_thickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r7", "r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").setIndex("size", 0.05, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("size", new String[]{"0.05", "Cu_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").setIndex("pos", 0.1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0.1", "0.055-Al_thickness-Cu_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r8", "r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("size", new String[]{"0.05", "Semi_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"0.1", "0.055-Al_thickness-Semi_thickness"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r3", "r4", "r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("r1", "r5", "r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 6, 9);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("coord1", new double[]{0.05, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("coord2", new double[]{0.15, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new double[]{0.05, 0.055});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new double[]{0.15, 0.055});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("del1", "ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0.16 0.31 0.31 0.31 0.31 0.26 0.26 0.26 0.21 0.21 0.21");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "0 0 0 0.052  0.052 0.052 0.052 0.052 0.052 0 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("csol1", 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").set("fil1", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "0.01+Al_thickness");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil3").selection("point").set("pol1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil3").set("radius", 0.01);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.1, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.2, 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("ext1", 2, 3, 7);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1", 50, 52, 56);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("del2", 14);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new double[]{0.01, 0.01});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new double[]{-0.025, -0.005});
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2")
         .label("\u4f53\u51e0\u4f55\u7ed3\u6784\u4e2d\u7684\u9988\u7535\u9762");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 0.16);
    model.component("comp1").geom("geom1").feature("copy1")
         .set("displz", "-Cu_thickness-Air_thickness-Al_thickness");
    model.component("comp1").geom("geom1").feature("copy1")
         .label("\u58f3\u51e0\u4f55\u7ed3\u6784\u4e2d\u7684\u9988\u7535\u9762");
    model.component("comp1").geom("geom1").feature("copy1").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u5b9e\u4f53\uff1a\u94dd");
    model.component("comp1").material("matlnk1").set("link", "mat2");
    model.component("comp1").material("matlnk1").selection().set(4);
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u5b9e\u4f53\uff1a\u94dc");
    model.component("comp1").material("matlnk2").set("link", "mat3");
    model.component("comp1").material("matlnk2").selection().set(1, 2);
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").label("\u5b9e\u4f53\uff1a\u534a\u5bfc\u4f53\u6750\u6599");
    model.component("comp1").material("matlnk3").set("link", "mat4");
    model.component("comp1").material("matlnk3").selection().set(5);
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").label("\u58f3\uff1aAl-Air-Cu");
    model.component("comp1").material("llmat1").selection().set(50, 51);
    model.component("comp1").material("llmat1").set("middlePlane", "userDef");
    model.component("comp1").material("llmat1")
         .set("offset", "-(Cu_thickness+Air_thickness)/(Cu_thickness+Air_thickness+Al_thickness)");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material().create("llmat2", "LayeredMaterialLink");
    model.component("comp1").material("llmat2").label("\u58f3\uff1aCu-Semi-Al");
    model.component("comp1").material("llmat2").set("link", "lmat2");
    model.component("comp1").material("llmat2").selection().set(58);
    model.component("comp1").material("llmat2").set("middlePlane", "bottom");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").label("Aluminum");
    model.component("comp1").material("mat5").set("family", "aluminum");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat6").label("Copper");
    model.component("comp1").material("mat6").set("family", "copper");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat6").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat5").selection().geom("geom1", 2);
    model.component("comp1").material("mat5").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat5").propertyGroup("shell").set("lth", "Al_thickness");
    model.component("comp1").material("mat5").label("\u58f3\uff1aAl");
    model.component("comp1").material("mat5").selection().set(53, 55, 56);
    model.component("comp1").material("mat5").set("middlePlane", "userDef");
    model.component("comp1").material("mat5").set("offset", "1+2*(Semi_thickness+Cu_thickness)/Al_thickness");
    model.component("comp1").material("mat6").selection().geom("geom1", 2);
    model.component("comp1").material("mat6").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat6").propertyGroup("shell").set("lth", "Cu_thickness");
    model.component("comp1").material("mat6").label("\u58f3\uff1aCu");
    model.component("comp1").material("mat6").selection().set(49, 52, 54, 57, 59, 60);
    model.component("comp1").material("mat6").set("middlePlane", "bottom");

    model.component("comp1").physics("ec").selection().set(1, 2, 4, 5);
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(1, 5);
    model.component("comp1").physics("ec").create("term1", "Terminal", 2);
    model.component("comp1").physics("ec").feature("term1").set("I0", 1);
    model.component("comp1").physics("ec").feature("term1").selection().set(15);
    model.component("comp1").physics("ecis").prop("LayerSelection").set("bndType", "allShell");
    model.component("comp1").physics("ecis").feature("csh1").set("bndType", "allShell");
    model.component("comp1").physics("ecis").feature("csh1").create("term1", "Terminal", 2);
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("bndType", "allShell");
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").selection().set(51);
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("applyTo", "bottom");
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("I0", 1);
    model.component("comp1").physics("ecis").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("bgnd1").set("bndType", "allShell");
    model.component("comp1").physics("ecis").feature("bgnd1").selection().set(107, 109, 111);
    model.component("comp1").physics("ecis").feature("bgnd1").set("allLayers", false);
    model.component("comp1").physics("ecis").feature("bgnd1").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").feature("bgnd1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").physics("ecis").create("bgnd2", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("bgnd2").selection().set(105);
    model.component("comp1").physics("ecis").create("cls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "llmat1");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist", "mat5");
    model.component("comp1").physics("ecis").create("cls2", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls2").set("shelllist_src", "llmat1");
    model.component("comp1").physics("ecis").feature("cls2").set("shelllist", "mat6");
    model.component("comp1").physics("ecis").feature("cls2").setIndex("shelllist_lName", 0, 0, 0);
    model.component("comp1").physics("ecis").create("cls3", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls3").set("shelllist_src", "llmat2");
    model.component("comp1").physics("ecis").feature("cls3").set("shelllist", "mat5");
    model.component("comp1").physics("ecis").feature("cls3").setIndex("shelllist_lName", 0, 0, 0);
    model.component("comp1").physics("ecis").create("cls4", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls4").set("shelllist_src", "llmat2");
    model.component("comp1").physics("ecis").feature("cls4").set("shelllist", "mat6");
    model.component("comp1").physics("ecis").create("inl1", "InsulatingLayer", 2);
    model.component("comp1").physics("ecis").feature("inl1").selection().all();
    model.component("comp1").physics("ecis").feature("inl1").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").feature("inl1").setIndex("shelllist_lCheck", 0, 2, 0);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(5);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().set(4);
    model.component("comp1").mesh("mesh1").run("ftet2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection()
         .set(49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", 0.005);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(50, 51, 54);
    model.component("comp1").variable("var1").set("V_Al", "ecis.Vdown");
    model.component("comp1").variable("var1").descr("V_Al", "\u94dd\u5c42\u4e2d\u7684\u7535\u52bf");
    model.component("comp1").variable("var1").set("V_Cu", "ecis.Vup");
    model.component("comp1").variable("var1").descr("V_Cu", "\u94dc\u5c42\u4e2d\u7684\u7535\u52bf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(58);
    model.component("comp1").variable("var2").set("V_Al", "ecis.Vup", "\u94dd\u5c42\u4e2d\u7684\u7535\u52bf");
    model.component("comp1").variable("var2").set("V_Cu", "ecis.Vdown", "\u94dc\u5c42\u4e2d\u7684\u7535\u52bf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().set(53, 55, 56);
    model.component("comp1").variable("var3").set("V_Al", "ecis.Vav", "\u94dd\u5c42\u4e2d\u7684\u7535\u52bf");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().set(49, 52, 57, 59, 60);
    model.component("comp1").variable("var4").set("V_Cu", "ecis.Vav", "\u94dc\u5c42\u4e2d\u7684\u7535\u52bf");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ecis)");
    model.result("pg3").set("data", "lshl1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V2");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").label("\u5b9e\u4f53\u7535\u52bf");
    model.result("pg3").feature("vol1").set("data", "dset1");
    model.result("pg3").feature("vol1").set("inheritplot", "surf1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5b9e\u4f53\u8def\u5f84 A");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(24, 41, 50, 55, 70, 71, 72, 81);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5b9e\u4f53\u8def\u5f84 B");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(22, 44, 56, 100, 101, 102, 103, 104);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u58f3\u8def\u5f84 A-B");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(113, 123, 124, 127, 132, 139);

    model.component("comp1").variable("var1").set("V_A", "V_Cu");
    model.component("comp1").variable("var1").descr("V_A", "\u7535\u52bf\u8def\u5f84 A");
    model.component("comp1").variable("var1").set("V_B", "V_Cu");
    model.component("comp1").variable("var1").descr("V_B", "\u7535\u52bf\u8def\u5f84 B");
    model.component("comp1").variable("var2").set("V_A", "V_Cu", "\u7535\u52bf\u8def\u5f84 A");
    model.component("comp1").variable("var2").set("V_B", "V_Al", "\u7535\u52bf\u8def\u5f84 B");
    model.component("comp1").variable("var3").set("V_A", "V_Al", "\u7535\u52bf\u8def\u5f84 A");
    model.component("comp1").variable("var3").set("V_B", "V_Al", "\u7535\u52bf\u8def\u5f84 B");
    model.component("comp1").variable("var4").set("V_A", "V_Cu", "\u7535\u52bf\u8def\u5f84 A");
    model.component("comp1").variable("var4").set("V_B", "V_Cu", "\u7535\u52bf\u8def\u5f84 B");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5b9e\u4f53-\u58f3\u6bd4\u8f83");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5b9e\u4f53-\u58f3\u6bd4\u8f83");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u52bf (V)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("sel1");
    model.result("pg4").feature("lngr1").set("linecolor", "blue");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u5b9e\u4f53\u4e09\u7ef4\u8def\u5f84 A", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").selection().named("sel3");
    model.result("pg4").feature("lngr2").set("expr", "V_A");
    model.result("pg4").feature("lngr2").set("linecolor", "blue");
    model.result("pg4").feature("lngr2").set("linestyle", "none");
    model.result("pg4").feature("lngr2").set("linemarker", "circle");
    model.result("pg4").feature("lngr2").set("markerpos", "interp");
    model.result("pg4").feature("lngr2").set("markers", 28);
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u591a\u5c42\u58f3\u8def\u5f84 A", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr3").set("linewidth", "preference");
    model.result("pg4").feature("lngr3").selection().named("sel2");
    model.result("pg4").feature("lngr3").set("linecolor", "red");
    model.result("pg4").feature("lngr3").set("legend", true);
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3").setIndex("legends", "\u5b9e\u4f53\u4e09\u7ef4\u8def\u5f84 B", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr4", "LineGraph");
    model.result("pg4").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr4").set("linewidth", "preference");
    model.result("pg4").feature("lngr4").selection().named("sel3");
    model.result("pg4").feature("lngr4").set("expr", "V_B");
    model.result("pg4").feature("lngr4").set("linecolor", "red");
    model.result("pg4").feature("lngr4").set("linestyle", "none");
    model.result("pg4").feature("lngr4").set("linemarker", "asterisk");
    model.result("pg4").feature("lngr4").set("markerpos", "interp");
    model.result("pg4").feature("lngr4").set("markers", 30);
    model.result("pg4").feature("lngr4").set("legend", true);
    model.result("pg4").feature("lngr4").set("legendmethod", "manual");
    model.result("pg4").feature("lngr4").setIndex("legends", "\u591a\u5c42\u58f3\u8def\u5f84 B", 0);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "dset1");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("trn1").set("move", new double[]{0, 0, 0.07});

    model.view("view8").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u5b9e\u4f53\u591a\u5c42\u58f3\u6bd4\u8f83");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u57fa\u51c6\u6d4b\u8bd5\uff0c\u7ed3\u679c\u8868\u660e\uff0c\u53ef\u4ee5\u4f7f\u7528\u7535\u6d41\uff0c\u591a\u5c42\u58f3 \u7269\u7406\u573a\u63a5\u53e3\u83b7\u5f97\u6b63\u786e\u7684\u7ed3\u679c\uff0c\u5c31\u50cf\u5728\u57fa\u4e8e\u4e09\u7ef4\u5b9e\u4f53\u8868\u793a\u7684\u7535\u6d41 \u63a5\u53e3\u6c42\u89e3\u6a21\u578b\u65f6\u4e00\u6837\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("solid_multilayer_shell_comparison.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
