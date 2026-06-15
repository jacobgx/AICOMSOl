/*
 * acoustics_particulate_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class acoustics_particulate_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("pelw", "PoroelasticWavesSinglePhysics", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics().create("psb1", "PorousStructureBoundary", 1);
    model.component("comp1").multiphysics("psb1").set("Porous_physics", "pelw");
    model.component("comp1").multiphysics("psb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("psb1").selection().all();
    model.component("comp1").multiphysics().create("apb1", "AcousticPorousBoundary", 1);
    model.component("comp1").multiphysics("apb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("apb1").set("Porous_physics", "pelw");
    model.component("comp1").multiphysics("apb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/psb1", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/apb1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p0", "1[Pa]", "\u5165\u5c04\u538b\u529b");
    model.param().set("tauP", "1", "\u66f2\u6298");
    model.param().set("Rfilter", "150[mm]", "\u8fc7\u6ee4\u534a\u5f84");
    model.param().set("Rtube", "50[mm]", "\u5165\u53e3/\u51fa\u53e3\u7ba1\u534a\u5f84");
    model.param().set("Lfilter", "200[mm]", "\u591a\u5b54\u8fc7\u6ee4\u5668\u957f\u5ea6");
    model.param().set("Lair", "50[mm]", "\u8fc7\u6ee4\u5668\u4e2d\u7a7a\u6c14\u90e8\u5206\u7684\u957f\u5ea6");
    model.param().set("Ltube", "100[mm]", "\u5165\u53e3/\u51fa\u53e3\u7ba1\u957f\u5ea6");
    model.param().set("dh", "5[mm]", "\u7a7a\u6c14\u901a\u9053\u5bbd\u5ea6");
    model.param().set("ht", "3.2[mm]", "\u591a\u5b54\u58c1\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rtube", "Ltube"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Rfilter", "Lair"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "Ltube"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Rfilter", "Lfilter"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "Ltube+Lair"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "ht", 0);
    model.component("comp1").geom("geom1").feature("r3").set("layertop", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Rfilter", "Lair"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "Ltube+Lair+Lfilter"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"Rtube", "Ltube"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "Ltube+2*Lair+Lfilter"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"dh", "Lfilter"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"ht", "Ltube+Lair"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r6");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{18, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"(dh+ht)", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u591a\u5b54\u5f39\u6027\u6ce2\u57df");
    model.component("comp1").selection("sel1")
         .set(3, 4, 5, 11, 12, 13, 17, 18, 19, 23, 24, 25, 29, 30, 31, 35, 36, 37, 41, 42, 43, 47, 48, 49, 53, 54, 55, 59, 60, 61, 65, 66, 67, 71, 72, 73, 77, 78, 79, 83, 84, 85, 89, 90, 91, 95, 96, 97, 101, 102, 103, 107, 108, 109, 113, 114, 115);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u94a2\u6750\u57df");
    model.component("comp1").selection("sel2")
         .set(10, 14, 22, 26, 34, 38, 46, 50, 58, 62, 70, 74, 82, 86, 94, 98, 106, 110);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7a7a\u6c14\u57df");
    model.component("comp1").selection("dif1").set("add", new String[]{"box1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1", "sel2"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78b3\u5316\u7845\u57fa\u4f53");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Steel AISI 4340");
    model.component("comp1").material("mat3").set("family", "steel");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat3").selection().named("sel2");

    model.component("comp1").physics("pelw").selection().named("sel1");
    model.component("comp1").physics("pelw").feature("pm1").set("PoroelasticModel", "Biot");
    model.component("comp1").physics("pelw").feature("pm1").set("SolidMaterial", "mat2");
    model.component("comp1").physics("pelw").feature("pm1").set("IsotropicOption", "Enu");
    model.component("comp1").physics("solid").selection().named("sel2");
    model.component("comp1").physics("pelw").create("pfix1", "Fixed", 1);
    model.component("comp1").physics("pelw").feature("pfix1").selection().set(273, 274, 275);
    model.component("comp1").physics("acpr").selection().named("dif1");
    model.component("comp1").physics("acpr").create("port1", "Port", 1);
    model.component("comp1").physics("acpr").feature("port1").selection().set(2);
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", "p0");
    model.component("comp1").physics("acpr").create("port2", "Port", 1);
    model.component("comp1").physics("acpr").feature("port2").selection().set(15);
    model.component("comp1").physics("acpr").feature("port2").set("PortType", "Circular");

    model.component("comp1").material("mat1").propertyGroup("def").set("compressibility", new String[]{"1/1[atm]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"20[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-11"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroelasticModel", "PoroelasticModel", "Poroelastic_material");
    model.component("comp1").material("mat2").propertyGroup("PoroelasticModel").set("alphaB", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("tau", new String[]{"tauP"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "ht");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2, 6);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(6, 12, 17, 22, 24, 29, 31, 36, 38, 43, 45, 50, 52, 57, 59, 64, 66, 71, 73, 78, 80, 85, 87, 92, 94, 99, 105, 110, 112, 117, 119, 124, 126, 131, 133, 138, 140, 145, 147, 152, 154, 159, 161, 166, 168, 173, 175, 180, 182, 187, 189, 194, 196, 201, 203, 208, 210, 215, 217, 222, 224, 229, 231, 236, 238, 243, 245, 250, 252, 257, 259, 264, 266, 271);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "ht/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "range(20,20,2000)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 100, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 100, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 100, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 100, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 100, 0);
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().dataset("dset1solidrev").set("modenumber", "solid.mk");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1solidrev");
    model.result("pg6").setIndex("looplevel", 100, 0);
    model.result("pg6").label("\u5e94\u529b, 3D (solid)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg6").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("descractive", true);
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 3");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("hasspacevars", "on");
    model.result().dataset("rev2").set("spacevars", new String[]{"rev1x", "rev1y", "rev1z"});
    model.result().dataset("rev2").set("anglevar", "rev1phi");
    model.result().dataset("rev2").set("data", "dset1");
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection()
         .set(3, 4, 5, 11, 12, 13, 17, 18, 19, 23, 24, 25, 29, 30, 31, 35, 36, 37, 41, 42, 43, 47, 48, 49, 53, 54, 55, 59, 60, 61, 65, 66, 67, 71, 72, 73, 77, 78, 79, 83, 84, 85, 89, 90, 91, 95, 96, 97, 101, 102, 103, 107, 108, 109, 113, 114, 115);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u4f4d\u79fb (pelw)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "pelw.disp");
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("def1", "Deform");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg7").feature("surf1").feature("def1").set("expr", new String[]{"u2", "w2"});
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u4e09\u7ef4\u4f4d\u79fb (pelw)");
    model.result("pg8").set("data", "rev2");
    model.result("pg8").setIndex("looplevel", 100, 0);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "pelw.disp");
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("def1", "Deform");
    model.result("pg8").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg8").feature("surf1").feature("def1")
         .set("expr", new String[]{"cos(rev1phi)*u2", "sin(rev1phi)*u2", "w2"});
    model.result("pg1").run();
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "solid.disp");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").set("titletype", "none");
    model.result("pg7").feature("surf2").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("expr", "solid.disp");
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").set("titletype", "none");
    model.result("pg8").feature("surf2").create("def1", "Deform");
    model.result("pg8").feature("surf2").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("def1").set("expr", new String[]{"cos(rev1phi)*u2", "v", "w"});
    model.result("pg8").feature("surf2").feature("def1").setIndex("expr", "sin(rev1phi)*v2", 1);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u5165\u53e3\u548c\u51fa\u53e3\u5904\u7684\u58f0\u538b\u7ea7");
    model.result("pg9").create("oct1", "OctaveBand");
    model.result("pg9").feature("oct1").set("quantity", "bandpower");
    model.result("pg9").feature("oct1").set("markerpos", "datapoints");
    model.result("pg9").feature("oct1").set("linewidth", "preference");
    model.result("pg9").feature("oct1").selection().geom("geom1", 1);
    model.result("pg9").feature("oct1").selection().set(15);
    model.result("pg9").feature("oct1").set("quantity", "continuous");
    model.result("pg9").feature("oct1").set("legend", true);
    model.result("pg9").feature("oct1").set("legendmethod", "manual");
    model.result("pg9").feature("oct1").setIndex("legends", "\u51fa\u53e3\u5904\u7684\u58f0\u538b\u7ea7", 0);
    model.result("pg9").feature().duplicate("oct2", "oct1");
    model.result("pg9").run();
    model.result("pg9").feature("oct2").selection().set(2);
    model.result("pg9").feature("oct2").setIndex("legends", "\u5165\u53e3\u5904\u7684\u58f0\u538b\u7ea7", 0);
    model.result("pg9").run();
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").set("xlog", false);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u4f20\u8f93\u635f\u8017");
    model.result("pg10").create("oct1", "OctaveBand");
    model.result("pg10").feature("oct1").set("quantity", "bandpower");
    model.result("pg10").feature("oct1").set("markerpos", "datapoints");
    model.result("pg10").feature("oct1").set("linewidth", "preference");
    model.result("pg10").feature("oct1").selection().geom("geom1");
    model.result("pg10").feature("oct1").set("expr", "acpr.port1.P_in");
    model.result("pg10").feature("oct1").set("exprtype", "power");
    model.result("pg10").feature("oct1").set("powerref", "acpr.port2.P_out");
    model.result("pg10").feature("oct1").set("quantity", "continuous");
    model.result("pg10").run();
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u4f20\u8f93\u635f\u8017 (dB)");
    model.result("pg10").set("xlog", false);
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u9897\u7c92\u8fc7\u6ee4\u578b\u7cfb\u7edf\u7684\u58f0\u5b66");

    model
         .description("\u8fd9\u4e2a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u4f7f\u7528\u201c\u591a\u5b54\u5f39\u6027\u6ce2\u201d\u63a5\u53e3\u8ba1\u7b97\u7a7f\u8fc7\u9897\u7c92\u8fc7\u6ee4\u578b\u7cfb\u7edf\u7684\u58f0\u4f20\u64ad\u635f\u5931\u3002\u672c\u4f8b\u8026\u5408\u4e86\u5165\u53e3\u548c\u51fa\u53e3\u7684\u58f0\u538b\u53ca\u6ee4\u6ce2\u77e9\u9635\u4e2d\u7684\u591a\u5b54\u5f39\u6027\u6ce2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("acoustics_particulate_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
