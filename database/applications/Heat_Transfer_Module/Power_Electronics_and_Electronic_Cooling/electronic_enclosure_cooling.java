/*
 * electronic_enclosure_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:27 by COMSOL 6.3.0.290. */
public class electronic_enclosure_cooling {

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
    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");

    model.param().set("OR", "0.4");
    model.param().descr("OR", "\u683c\u6805\u7684\u5f00\u53e3\u7387");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "electronic_enclosure_cooling.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(1, 2, 4, 41, 776);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5851\u6599");
    model.component("comp1").selection("sel2").set(6, 7, 10, 11, 14, 20, 21, 22, 25, 26, 30, 37, 38, 41, 42, 45, 46);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7fc5\u7247");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 292, 322, 378, 441, 495, 529, 636, 651, 666, 677);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5c01\u88c5\u8fb9");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(1, 2, 4, 41, 776);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u94dd\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u53d8\u538b\u5668\u58f3");
    model.component("comp1").selection("sel5").set(3, 8, 9, 18, 23, 24, 34, 39, 40);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5c0f\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("sel6").set(12, 27);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u5927\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("sel7").set(43);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel6", "sel7"});
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u7535\u611f\u5668");
    model.component("comp1").selection("sel8").set(5, 19, 33);
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u94a2\u5236\u96f6\u4ef6");
    model.component("comp1").selection("uni3").set("input", new String[]{"sel5", "sel8"});
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u5927\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel9").set(17, 48);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u4e2d\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel10").set(4, 15, 29, 35, 44, 50);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u5c0f\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel11").set(2, 13, 31, 36);
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u7535\u5bb9\u5668");
    model.component("comp1").selection("uni4").set("input", new String[]{"sel9", "sel10", "sel11"});
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u6676\u4f53\u7ba1\u7845\u82af\u7247");
    model.component("comp1").selection("sel12").set(16, 28, 32, 47, 49);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u94dc\u5c42");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel8"});
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u683c\u6805");
    model.component("comp1").selection("sel13").geom(2);
    model.component("comp1").selection("sel13").set(777);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u98ce\u6247");
    model.component("comp1").selection("sel14").geom(2);
    model.component("comp1").selection("sel14").set(87);
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u7535\u8def\u677f");
    model.component("comp1").selection("sel15").geom(2);
    model.component("comp1").selection("sel15").set(3);
    model.component("comp1").selection("sel15").set("groupcontang", true);
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("\u4f20\u5bfc\u5c42");
    model.component("comp1").selection("uni5").set("entitydim", 2);
    model.component("comp1").selection("uni5").set("input", new String[]{"uni1", "adj1", "sel15"});
    model.component("comp1").selection().create("sel16", "Explicit");
    model.component("comp1").selection("sel16").label("\u7535\u7ebf\u7ec4\u8868\u9762");
    model.component("comp1").selection("sel16").geom(2);
    model.component("comp1").selection("sel16")
         .set(538, 539, 556, 557, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774);
    model.component("comp1").selection().create("sel17", "Explicit");
    model.component("comp1").selection("sel17").label("\u5c0f\u578b\u7535\u7ebf\u8868\u9762");
    model.component("comp1").selection("sel17").geom(2);
    model.component("comp1").selection("sel17").set(82, 83, 84, 85);
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u5143\u4ef6\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel2", "uni2", "uni3", "uni4", "sel12"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6362\u70ed\u8868\u9762");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel3", "adj2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel15"});
    model.component("comp1").selection().create("sel18", "Explicit");
    model.component("comp1").selection("sel18").label("\u5f2f\u66f2\u533a\u57df");
    model.component("comp1").selection("sel18").geom(2);
    model.component("comp1").selection("sel18")
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 86, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135);
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("\u58c1");
    model.component("comp1").selection("uni6").set("entitydim", 2);
    model.component("comp1").selection("uni6").set("input", new String[]{"uni5", "sel16", "sel17", "dif1", "sel18"});

    model.title("\u53c2\u6570\u5316\u7684\u7535\u5668\u673a\u7bb1\u51e0\u4f55");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f mph \u6587\u4ef6\uff0c\u5305\u542b\u201c\u542b\u98ce\u6247\u548c\u683c\u6805\u7684\u5c01\u95ed\u7a7a\u95f4\u7684\u5f3a\u5236\u5bf9\u6d41\u6563\u70ed\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("electronic_enclosure_cooling_geom.mph");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "k_grille");
    model.func("an1").set("expr", "12084*OR^6-42281*OR^5+60989*OR^4-46559*OR^3+19963*OR^2-4618.5*OR+462.89");
    model.func("an1").set("args", "OR");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").set("fununit", "m^-4");
    model.func("an1").setIndex("plotargs", 0.8, 0, 2);

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Acrylic plastic");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 1000);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
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
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat4").label("Aluminum");
    model.component("comp1").material("mat4").set("family", "aluminum");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat5").label("Copper");
    model.component("comp1").material("mat5").set("family", "copper");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat5").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat6", "Common");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat6").label("Silicon");
    model.component("comp1").material("mat6").set("family", "custom");
    model.component("comp1").material("mat6").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat6").set("diffuse", "custom");
    model.component("comp1").material("mat6")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat6").set("ambient", "custom");
    model.component("comp1").material("mat6")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat6").set("noise", true);
    model.component("comp1").material("mat6").set("fresnel", 0.7);
    model.component("comp1").material("mat6").set("roughness", 0.5);
    model.component("comp1").material("mat6").set("diffusewrap", 0);
    model.component("comp1").material("mat6").set("reflectance", 0);
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat6").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat6").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat7").label("Aluminum 6063-T83");
    model.component("comp1").material("mat7").set("family", "aluminum");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat7").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat7").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat7").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat8").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat8").label("Copper 1");
    model.component("comp1").material("mat8").set("family", "copper");
    model.component("comp1").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat8").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat8").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat8").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat8").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat8").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat8").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat8").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat8").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat8").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").selection().named("uni3");
    model.component("comp1").material("mat4").selection().named("uni4");
    model.component("comp1").material("mat5").selection().named("uni2");
    model.component("comp1").material("mat6").selection().named("sel12");
    model.component("comp1").material("mat7").label("\u6563\u70ed\u5668");
    model.component("comp1").material("mat7").selection().geom("geom1", 2);
    model.component("comp1").material("mat7").selection().named("uni1");
    model.component("comp1").material("mat7").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat7").propertyGroup("shell").set("lth", new String[]{"2[mm]"});
    model.component("comp1").material("mat8").label("\u94dc\u5c42");
    model.component("comp1").material("mat8").selection().geom("geom1", 2);
    model.component("comp1").material("mat8").selection().named("adj1");
    model.component("comp1").material("mat8").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat8").propertyGroup("shell").set("lth", new String[]{"2[mm]"});
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").label("FR4\uff08\u7535\u8def\u677f\uff09");
    model.component("comp1").material("mat9").selection().geom("geom1", 2);
    model.component("comp1").material("mat9").selection().named("sel15");
    model.component("comp1").material("mat9").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat9").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat9").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat9").propertyGroup("shell").set("lth", new String[]{"2[mm]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("heatcapacity", new String[]{"1369[J/(kg*K)]"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", new String[]{"1900[kg/m^3]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"10[W/(m*K)]", "10[W/(m*K)]", "0.3[W/(m*K)]"});

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "30[degC]");

    model.component("comp1").physics("ht").feature("fluid1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").label("\u70ed\u6e90 1\uff1a\u6676\u4f53\u7ba1");
    model.component("comp1").physics("ht").feature("hs1").selection().named("sel12");
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "25[W]");
    model.component("comp1").physics("ht").create("hs2", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs2")
         .label("\u70ed\u6e90 2\uff1a\u5927\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").physics("ht").feature("hs2").selection().named("sel7");
    model.component("comp1").physics("ht").feature("hs2").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs2").set("P0", "5[W]");
    model.component("comp1").physics("ht").create("hs3", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs3")
         .label("\u70ed\u6e90 3\uff1a\u5c0f\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").physics("ht").feature("hs3").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs3").set("P0", "3[W]");
    model.component("comp1").physics("ht").feature("hs3").selection().named("sel6");
    model.component("comp1").physics("ht").create("hs4", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs4").label("\u70ed\u6e90 4\uff1a\u7535\u611f\u5668");
    model.component("comp1").physics("ht").feature("hs4").selection().named("sel8");
    model.component("comp1").physics("ht").feature("hs4").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs4").set("P0", "2[W]");
    model.component("comp1").physics("ht").create("hs5", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs5")
         .label("\u70ed\u6e90 5\uff1a\u5927\u578b\u7535\u5bb9\u5668");
    model.component("comp1").physics("ht").feature("hs5").selection().named("sel9");
    model.component("comp1").physics("ht").feature("hs5").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs5").set("P0", "2[W]");
    model.component("comp1").physics("ht").create("hs6", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs6")
         .label("\u70ed\u6e90 6\uff1a\u4e2d\u578b\u7535\u5bb9\u5668");
    model.component("comp1").physics("ht").feature("hs6").selection().named("sel10");
    model.component("comp1").physics("ht").feature("hs6").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs6").set("P0", "3[W]");
    model.component("comp1").physics("ht").create("hs7", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs7")
         .label("\u70ed\u6e90 7\uff1a\u5c0f\u578b\u7535\u5bb9\u5668");
    model.component("comp1").physics("ht").feature("hs7").selection().named("sel11");
    model.component("comp1").physics("ht").feature("hs7").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs7").set("P0", "1[W]");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel13");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().named("uni5");
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "Conductive");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel14");
    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("sel3");
    model.component("comp1").physics("spf").create("fan1", "ExtFan", 2);
    model.component("comp1").physics("spf").feature("fan1").selection().named("sel14");
    model.component("comp1").physics("spf").feature("fan1").set("FlowDirection", "Outlet");
    model.component("comp1").physics("spf").feature("fan1").set("FlowCondition", "StaticPressureCurveData");
    model.component("comp1").physics("spf").feature("fan1")
         .set("V0data", new String[]{"0", "0.000833333", "0.001666667", "0.0025", "0.002916667", "0.003333333", "0.00375", "0.004166667", "0.004583333", "0.004833333"});
    model.component("comp1").physics("spf").feature("fan1")
         .set("spcd", new double[]{12.3, 11.4, 9, 6.3, 6, 5.8, 4.3, 2.2, 0.7, 0});
    model.component("comp1").physics("spf").feature("fan1").set("InterpFunctionType", "PiecewiseCubic");
    model.component("comp1").physics("spf").create("grille1", "Grille", 2);
    model.component("comp1").physics("spf").feature("grille1").selection().named("sel13");
    model.component("comp1").physics("spf").feature("grille1").set("qlc", "k_grille(OR)*nitf1.rho/2");
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"-1[m/s]", "0", "0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel16");
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", 0.4);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("sel17");
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmax", 0.15);
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("sel18");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("uni6");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection()
         .set(1, 2, 3, 4, 41, 138, 139, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 158, 162, 165, 171, 172, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 189, 190, 191, 192, 193, 194, 222, 223, 224, 225, 239, 242, 260, 261, 281, 284, 292, 306, 307, 308, 309, 312, 313, 316, 317, 322, 328, 329, 334, 339, 342, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 378, 419, 420, 421, 422, 435, 436, 441, 446, 447, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 481, 484, 495, 508, 511, 517, 518, 529, 531, 534, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 553, 560, 561, 569, 570, 576, 579, 584, 587, 628, 629, 636, 643, 644, 645, 646, 651, 652, 653, 655, 658, 666, 669, 670, 677, 692, 693, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 733, 736, 749, 752, 776);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").label("\u57df");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").feature().create("vol2", "Volume");
    model.result("pg1").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").set("showsolutionparams", "on");
    model.result("pg1").feature("vol2").set("solutionparams", "parent");
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").set("smooth", "internal");
    model.result("pg1").feature("vol2").set("showsolutionparams", "on");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "lshl1");
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
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5185\u58c1");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 140, 141, 154, 155, 156, 157, 159, 160, 161, 163, 164, 166, 167, 168, 169, 170, 173, 174, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 206, 207, 210, 212, 214, 215, 216, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 232, 233, 238, 240, 241, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 282, 283, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 302, 304, 305, 306, 307, 308, 309, 310, 311, 314, 315, 316, 317, 322, 323, 324, 325, 326, 327, 330, 331, 332, 333, 335, 336, 338, 340, 341, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 390, 391, 394, 396, 398, 399, 400, 402, 403, 404, 405, 406, 407, 408, 409, 412, 413, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 441, 442, 443, 444, 445, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 482, 483, 485, 486, 487, 488, 489, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 503, 505, 506, 507, 509, 510, 512, 513, 514, 515, 516, 519, 520, 521, 522, 523, 524, 529, 530, 532, 533, 535, 536, 537, 538, 539, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 554, 555, 556, 557, 558, 559, 562, 563, 564, 565, 566, 567, 568, 571, 572, 573, 574, 575, 577, 578, 580, 581, 582, 583, 585, 586, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 602, 603, 606, 608, 610, 611, 612, 614, 615, 616, 617, 618, 619, 620, 621, 624, 625, 626, 627, 630, 631, 632, 633, 634, 635, 636, 637, 638, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 656, 657, 659, 660, 661, 666, 667, 668, 671, 672, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 732, 734, 735, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 750, 751, 753, 754, 755, 756, 757, 759, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776);
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("upexpr", "up(p)");
    model.result("pg3").feature("slit1").set("downexpr", "down(p)");
    model.result("pg3").feature("slit1").set("titletype", "none");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature().create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").label("\u58c1\u5206\u8fa8\u7387\uff0c\u5185\u58c1");
    model.result("pg4").feature("slit1").set("data", "surf2");
    model.result("pg4").feature("slit1").set("showsolutionparams", "on");
    model.result("pg4").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg4").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result("pg4").feature("slit1").set("smooth", "internal");
    model.result("pg4").feature("slit1").set("showsolutionparams", "on");
    model.result("pg4").feature("slit1").set("data", "surf2");
    model.result("pg4").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u58c1\u6e29");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 159, 160, 161, 163, 164, 166, 167, 168, 169, 170, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 206, 207, 210, 212, 214, 215, 216, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 232, 233, 238, 240, 241, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 282, 283, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 302, 304, 305, 306, 307, 308, 309, 310, 311, 314, 315, 316, 317, 322, 323, 324, 325, 326, 327, 330, 331, 332, 333, 335, 336, 338, 340, 341, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 390, 391, 394, 396, 398, 399, 400, 402, 403, 404, 405, 406, 407, 408, 409, 412, 413, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 441, 442, 443, 444, 445, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 482, 483, 485, 486, 487, 488, 489, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 503, 505, 506, 507, 509, 510, 512, 513, 514, 515, 516, 519, 520, 521, 522, 523, 524, 529, 530, 532, 533, 535, 536, 537, 538, 539, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 554, 555, 556, 557, 558, 559, 562, 563, 564, 565, 566, 567, 568, 571, 572, 573, 574, 575, 577, 578, 580, 581, 582, 583, 585, 586, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 602, 603, 606, 608, 610, 611, 612, 614, 615, 616, 617, 618, 619, 620, 621, 624, 625, 626, 627, 630, 631, 632, 633, 634, 635, 636, 637, 638, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 656, 657, 659, 660, 661, 666, 667, 668, 671, 672, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 732, 734, 735, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 750, 751, 753, 754, 755, 756, 757, 759, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "nitf1.T");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg5").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg5").feature("vol1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50);
    model.result("pg5").feature("vol1").set("inheritplot", "surf1");
    model.result("pg5").feature().create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("solutionparams", "parent");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg5").feature("arwv1").set("xnumber", 30);
    model.result("pg5").feature("arwv1").set("ynumber", 30);
    model.result("pg5").feature("arwv1").set("znumber", 30);
    model.result("pg5").feature("arwv1").set("arrowtype", "cone");
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("data", "parent");
    model.result("pg5").feature("arwv1").feature().create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg5").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("vol1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("colortable", "HeatCameraLight");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("inheritplot", "vol2");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg1").feature("str1").selection().named("sel13");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg1").feature("str1").feature("col1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg1").run();

    model
         .title("\u542b\u98ce\u6247\u548c\u683c\u6805\u7684\u5c01\u95ed\u7a7a\u95f4\u7684\u5f3a\u5236\u5bf9\u6d41\u6563\u70ed");

    model
         .description("\u672c\u7814\u7a76\u6a21\u62df\u8ba1\u7b97\u673a\u7535\u6e90\u4f9b\u5e94\u5668 (PSU) \u7684\u70ed\u6027\u80fd\u3002\u8fd9\u79cd\u7535\u5b50\u8bbe\u5907\u5916\u58f3\u901a\u5e38\u5305\u542b\u51b7\u5374\u88c5\u7f6e\uff0c\u4ee5\u907f\u514d\u7535\u5b50\u5143\u4ef6\u56e0\u6e29\u5ea6\u8fc7\u9ad8\u800c\u635f\u574f\u3002\u672c\u4f8b\u4e2d\uff0c\u5728\u6392\u6c14\u6247\u548c\u591a\u5b54\u683c\u6805\u7684\u4f5c\u7528\u4e0b\uff0c\u5916\u58f3\u5185\u7684\u6c14\u6d41\u53ef\u4ee5\u5b9e\u73b0\u5185\u90e8\u6563\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electronic_enclosure_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
