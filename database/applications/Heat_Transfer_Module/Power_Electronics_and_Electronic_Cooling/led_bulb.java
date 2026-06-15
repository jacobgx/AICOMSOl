/*
 * led_bulb.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:27 by COMSOL 6.3.0.290. */
public class led_bulb {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("power", "5[W]/4");
    model.param().descr("power", "5W LED \u706f\u6ce1\u7684\u56db\u5206\u4e4b\u4e00\u7684\u6807\u79f0\u529f\u7387");
    model.param().set("eta", "0.3");
    model.param().descr("eta", "\u6548\u7387");
    model.param().set("heat_losses", "(1-eta)*power");
    model.param().descr("heat_losses", "\u90e8\u5206\u529f\u7387\u901a\u8fc7\u70ed\u91cf\u8017\u6563");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "led_bulb.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 44);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 261);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 43);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 41);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 42);

    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("LED");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("imp1", 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("PCB");

    model.component("comp1").view("view1").hideObjects().create("hide2");
    model.component("comp1").view("view1").hideObjects("hide2").init(3);
    model.component("comp1").view("view1").hideObjects("hide2").add("imp1", 1);
    model.component("comp1").view("view1").hideObjects("hide2").add("imp1", 3);

    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1", 4, 5, 7, 29);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("PCB \u8f90\u5c04\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("imp1", 21, 49, 53, 82, 83, 84, 85, 86, 87, 98, 126, 127, 128, 129, 130, 131, 143, 169, 170, 171, 172, 173, 174, 188, 189, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("imp1", 13);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("imp1", 3, 44, 261);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("ht").feature("fluid1").selection().set(1);
    model.component("comp1").physics("spf").selection().set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Aluminum");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Acrylic plastic");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("lighting", "phong");
    model.component("comp1").material("mat4").set("shininess", 1000);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Filled epoxy resin (X238) [solid]");
    model.component("comp1").material("mat5").info().create("\u7ec4\u5206");
    model.component("comp1").material("mat5").info("\u7ec4\u5206")
         .body("35 vol % powdered aluminum + 35 vol % SiO\u0082");
    model.component("comp1").material("mat5").info("\u7ec4\u5206").title("\u7ec4\u5206");
    model.component("comp1").material("mat5").propertyGroup("def").set("thermalconductivity", "k(T)");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "\u5f15\u7528: V. Giaretto and M.F. Torchio, \"Estimation of the thermal conductivity of an epoxy resin by the use of an internal parallelepiped heat source. II: Experimental analysis\", High Temperatures-High Pressures, v31, No. 6, p643 (1999)\n\u6ce8: Ciba-Geigy");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "1673[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("density", "\u5f15\u7528: V. Giaretto and M.F. Torchio, \"Estimation of the thermal conductivity of an epoxy resin by the use of an internal parallelepiped heat source. II: Experimental analysis\", High Temperatures-High Pressures, v31, No. 6, p643 (1999)\n\u6ce8: Ciba-Geigy, room temperature value");
    model.component("comp1").material("mat5").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat5").propertyGroup("def").func("k").set("funcname", "k");
    model.component("comp1").material("mat5").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat5").propertyGroup("def").func("k").set("extrap", "constant");
    model.component("comp1").material("mat5").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"293.0", "353.16", "-0.03037232+0.001554161*T^1"}});
    model.component("comp1").material("mat5").propertyGroup("def").func("k").label("Piecewise");
    model.component("comp1").material("mat5").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat5").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat5").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5").set("lighting", "cooktorrance");
    model.component("comp1").material("mat5").set("specular", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.7058823529411765, 0.7058823529411765, 0.7058823529411765});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.47058823529411764, 0.47058823529411764, 0.47058823529411764});
    model.component("comp1").material("mat5").set("fresnel", 0.3);
    model.component("comp1").material("mat5").set("roughness", 0.6);
    model.component("comp1").material("mat5").set("metallic", 0);
    model.component("comp1").material("mat5").set("pearl", 0);
    model.component("comp1").material("mat5").set("noisescale", 0.45);
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("noisefreq", 3);
    model.component("comp1").material("mat5").set("normalnoisebrush", "0");
    model.component("comp1").material("mat5").set("normalnoisetype", "0");
    model.component("comp1").material("mat5").set("colornoisescale", 0);
    model.component("comp1").material("mat5").set("colornoise", false);
    model.component("comp1").material("mat5").set("colornoisefrequency", 1);
    model.component("comp1").material("mat5").set("colornoisebrush", "0");
    model.component("comp1").material("mat5").set("colornoisetype", "0");
    model.component("comp1").material("mat5").set("colornoisenormalscale", 0);
    model.component("comp1").material("mat5").set("noisecolor", "custom");
    model.component("comp1").material("mat5").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat5").set("noisecolorblend", 0);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("clearcoat", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").set("shininess", 100);
    model.component("comp1").material("mat5").set("specular", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat5").set("noisescale", 0.05);
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("noisefreq", 2.1);
    model.component("comp1").material("mat1").label("\u706f\u6ce1\u5e95\u5ea7");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("hidestatus", "ignore");

    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat3").label("PCB");
    model.component("comp1").material("mat3").selection().named("geom1_sel2");
    model.component("comp1").material("mat4").label("\u5851\u6599\u706f\u6ce1");
    model.component("comp1").material("mat4").selection().set(3);
    model.component("comp1").material("mat5").label("LED \u82af\u7247");
    model.component("comp1").material("mat5").selection().named("geom1_sel1");
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"1200[J/kg/K]"});

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("UseReducedPressure", true);
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"0", "0", "1e-2[m/s]"});
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(3);
    model.component("comp1").physics("spf").feature("open1").set("f0", "-0.5*spf.rhoref*w^2");
    model.component("comp1").physics("spf").create("open2", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open2").selection().set(13, 44, 261);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1, 2, 7);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "heat_losses");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.9);
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ht").feature("open1").selection().set(3, 13, 44, 261);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection()
         .set(1, 2, 4, 5, 7, 9, 10, 14, 18, 22, 55, 56, 96, 100, 138, 184, 190, 194, 198, 202, 206, 210);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "4e-4");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size3").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(6, 8, 11, 12, 15, 16, 17, 19, 20, 21, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 41, 42, 43, 45, 46, 49, 50, 51, 53, 54, 57, 58, 60, 61, 62, 64, 65, 66, 68, 69, 70, 72, 73, 74, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 98, 99, 101, 102, 104, 105, 106, 108, 109, 110, 112, 113, 114, 116, 117, 118, 120, 121, 122, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 139, 140, 141, 143, 144, 146, 147, 148, 150, 151, 152, 154, 155, 156, 158, 159, 160, 162, 163, 164, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 187, 188, 191, 192, 195, 196, 199, 200, 203, 204, 207, 208, 211, 212, 213, 214, 215, 216, 217, 218, 220, 221, 222, 224, 225, 226, 228, 229, 230, 232, 233, 234, 236, 237, 238, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u6e29");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(6, 8, 11, 12, 15, 16, 17, 19, 20, 21, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 41, 42, 43, 45, 46, 49, 50, 51, 53, 54, 57, 58, 60, 61, 62, 64, 65, 66, 68, 69, 70, 72, 73, 74, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 98, 99, 101, 102, 104, 105, 106, 108, 109, 110, 112, 113, 114, 116, 117, 118, 120, 121, 122, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 139, 140, 141, 143, 144, 146, 147, 148, 150, 151, 152, 154, 155, 156, 158, 159, 160, 162, 163, 164, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 187, 188, 191, 192, 195, 196, 199, 200, 203, 204, 207, 208, 211, 212, 213, 214, 215, 216, 217, 218, 220, 221, 222, 224, 225, 226, 228, 229, 230, 232, 233, 234, 236, 237, 238, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "nitf1.T");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg4").feature("vol1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41);
    model.result("pg4").feature("vol1").set("inheritplot", "surf1");
    model.result("pg4").feature().create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("solutionparams", "parent");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg4").feature("arwv1").set("xnumber", 30);
    model.result("pg4").feature("arwv1").set("ynumber", 30);
    model.result("pg4").feature("arwv1").set("znumber", 30);
    model.result("pg4").feature("arwv1").set("arrowtype", "cone");
    model.result("pg4").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("data", "parent");
    model.result("pg4").feature("arwv1").feature().create("col1", "Color");
    model.result("pg4").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg4").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset("mir2").set("removesymelem", true);

    model.component("comp1").view("view1").hideObjects("hide1").active(false);
    model.component("comp1").view("view1").hideObjects("hide2").active(false);

    model.result("pg4").run();
    model.result("pg4").set("data", "mir2");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("unit", "\u00b0C");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("unit", "\u00b0C");
    model.result("pg4").run();

    model.view("view2").set("transparency", true);
    model.view("view2").set("showaxisorientation", false);
    model.view("view2").set("showgrid", false);

    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("quickxnumber", 1);
    model.result("pg2").feature("slc1").set("data", "mir2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "mir2");
    model.result("pg2").run();

    model.view("view2").set("transparency", false);

    model.result("pg4").run();

    model.title("LED \u706f\u6ce1\u51b7\u5374");

    model
         .description("\u672c\u6a21\u578b\u4ee5\u4ee3\u8868\u706f\u6ce1\u548c\u5468\u56f4\u7a7a\u6c14\u7684\u771f\u5b9e\u51e0\u4f55\u7ed3\u6784\u63cf\u8ff0\u4e09\u79cd\u4f20\u70ed\u6a21\u5f0f\uff08\u4f20\u5bfc\u3001\u5bf9\u6d41\u548c\u8f90\u5c04\uff09\u4ee5\u53ca\u975e\u7b49\u6e29\u6d41\u52a8\u3002\nLED \u82af\u7247\u6563\u70ed\u3002\u8be5\u6a21\u578b\u8ba1\u7b97\u7531\u8fd9\u4e9b\u70ed\u6e90\u5f15\u8d77\u7684\u5e73\u8861\u6e29\u5ea6\u3001\u56fa\u4f53\u90e8\u5206\u7684\u4f20\u5bfc\u3001\u81ea\u7136\u5bf9\u6d41\u5f15\u8d77\u7684\u5bf9\u6d41\u51b7\u5374\u4ee5\u53ca\u5bf9\u5468\u56f4\u73af\u5883\u7684\u8f90\u5c04\u51b7\u5374\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("led_bulb.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
