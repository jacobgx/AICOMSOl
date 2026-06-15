/*
 * li_battery_spiral_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_spiral_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rp_neg", "12.5e-6[m]", "\u9897\u7c92\u534a\u5f84\uff0c\u8d1f\u6781");
    model.param().set("rp_pos", "8e-6[m]", "\u9897\u7c92\u534a\u5f84\uff0c\u6b63\u6781");
    model.param().set("T_ext", "300[K]", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("sigmas_neg", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "3.8[S/m]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("epss_pos", "1-epsl_pos-0.259", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("epsl_pos", "0.444", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("cl_0", "1000[mol/m^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param().set("epss_neg", "1-epsl_neg-0.172", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epsl_neg", "0.357", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("cs0_neg", "20700[mol/m^3]", "\u8d1f\u6781\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("cs0_pos", "4000[mol/m^3]", "\u6b63\u6781\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param()
         .set("i0ref_neg", "0.96[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0ref_pos", "0.70[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("aA_pos", "0.5", "\u53cd\u5e94\u901f\u7387\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("aC_pos", "0.5", "\u53cd\u5e94\u901f\u7387\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("aA_neg", "0.5", "\u53cd\u5e94\u901f\u7387\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param().set("aC_neg", "0.5", "\u53cd\u5e94\u901f\u7387\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param().set("r0", "0.00176[m]", "\u87ba\u65cb\u8d77\u59cb\u534a\u5f84");
    model.param().set("a1", "60e-6[m]", "\u6b63\u591a\u5b54\u7535\u6781 1 \u7684\u539a\u5ea6");
    model.param().set("a2", "20e-6[m]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("a3", "60e-6[m]", "\u6b63\u591a\u5b54\u7535\u6781 2 \u7684\u539a\u5ea6");
    model.param().set("a4", "30e-6[m]", "\u9694\u819c 1 \u7684\u539a\u5ea6");
    model.param().set("a5", "60e-6[m]", "\u8d1f\u591a\u5b54\u7535\u6781 1 \u7684\u539a\u5ea6");
    model.param().set("a6", "14e-6[m]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("a7", "60e-6[m]", "\u8d1f\u591a\u5b54\u7535\u6781 2 \u7684\u539a\u5ea6");
    model.param().set("a8", "30e-6[m]", "\u9694\u819c 2 \u7684\u539a\u5ea6");
    model.param().set("D_tot", "a1+a2+a3+a4+a5+a6+a7+a8", "\u7535\u6c60\u5355\u5143\u603b\u539a\u5ea6");
    model.param().set("laps", "3", "\u87ba\u65cb\u5708\u6570");
    model.param().set("w_tot", "2*pi*laps", "\u603b\u65cb\u8f6c\u89d2\u5ea6");
    model.param()
         .set("i_app_1C", "-3.3e4[A/m^2]", "1C \u65f6\u7535\u6c60\u7ec8\u7aef\u7684\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "spiralX");
    model.func("an1").set("expr", "-(s/(2*pi)*D_tot+R)*sin(s)");
    model.func("an1").set("args", "s, R");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", "m", 1);
    model.func("an1").set("fununit", "m");
    model.func().duplicate("an2", "an1");
    model.func("an2").set("funcname", "spiralY");
    model.func("an2").set("expr", "-(s/(2*pi)*D_tot+R)*cos(s)");

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", "w_tot+2*pi");
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"spiralX(s,r0)", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "spiralY(s,r0)", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("pc2").setIndex("coord", "spiralX(s,r0+a1)", 0);
    model.component("comp1").geom("geom1").feature("pc2").setIndex("coord", "spiralY(s,r0+a1)", 1);
    model.component("comp1").geom("geom1").run("pc2");
    model.component("comp1").geom("geom1").feature().duplicate("pc3", "pc2");
    model.component("comp1").geom("geom1").feature("pc3").setIndex("coord", "spiralX(s,r0+a1+a2)", 0);
    model.component("comp1").geom("geom1").feature("pc3").setIndex("coord", "spiralY(s,r0+a1+a2)", 1);
    model.component("comp1").geom("geom1").run("pc3");
    model.component("comp1").geom("geom1").feature().duplicate("pc4", "pc3");
    model.component("comp1").geom("geom1").feature("pc4").setIndex("coord", "spiralX(s,r0+a1+a2+a3)", 0);
    model.component("comp1").geom("geom1").feature("pc4").setIndex("coord", "spiralY(s,r0+a1+a2+a3)", 1);
    model.component("comp1").geom("geom1").run("pc4");
    model.component("comp1").geom("geom1").feature().duplicate("pc5", "pc4");
    model.component("comp1").geom("geom1").feature("pc5").setIndex("coord", "spiralX(s,r0+a1+a2+a3+a4)", 0);
    model.component("comp1").geom("geom1").feature("pc5").setIndex("coord", "spiralY(s,r0+a1+a2+a3+a4)", 1);
    model.component("comp1").geom("geom1").run("pc5");
    model.component("comp1").geom("geom1").feature().duplicate("pc6", "pc5");
    model.component("comp1").geom("geom1").feature("pc6").setIndex("coord", "spiralX(s,r0+a1+a2+a3+a4+a5)", 0);
    model.component("comp1").geom("geom1").feature("pc6").setIndex("coord", "spiralY(s,r0+a1+a2+a3+a4+a5)", 1);
    model.component("comp1").geom("geom1").run("pc6");
    model.component("comp1").geom("geom1").feature().duplicate("pc7", "pc6");
    model.component("comp1").geom("geom1").feature("pc7").setIndex("coord", "spiralX(s,r0+a1+a2+a3+a4+a5+a6)", 0);
    model.component("comp1").geom("geom1").feature("pc7").setIndex("coord", "spiralY(s,r0+a1+a2+a3+a4+a5+a6)", 1);
    model.component("comp1").geom("geom1").run("pc7");
    model.component("comp1").geom("geom1").feature().duplicate("pc8", "pc7");
    model.component("comp1").geom("geom1").feature("pc8").setIndex("coord", "spiralX(s,r0+a1+a2+a3+a4+a5+a6+a7)", 0);
    model.component("comp1").geom("geom1").feature("pc8").setIndex("coord", "spiralY(s,r0+a1+a2+a3+a4+a5+a6+a7)", 1);
    model.component("comp1").geom("geom1").run("pc8");
    model.component("comp1").geom("geom1").create("pc9", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc9").set("parmax", "D_tot*(laps+1)");
    model.component("comp1").geom("geom1").feature("pc9").set("coord", new String[]{"0", "-(s+r0)"});
    model.component("comp1").geom("geom1").run("pc9");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("pc1", "pc2", "pc3", "pc4", "pc5", "pc6", "pc7", "pc8", "pc9");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "D_tot*(laps+1.25)+r0");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"D_tot/4", "-D_tot/4"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel1").set(8, 16, 24);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel2").set(4, 12, 20);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6b63\u591a\u5b54\u7535\u6781");
    model.component("comp1").selection("sel3").set(7, 9, 15, 17, 23, 25);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u8d1f\u591a\u5b54\u7535\u6781");
    model.component("comp1").selection("sel4").set(3, 5, 11, 13, 19, 21);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u7535\u89e3\u8d28");
    model.component("comp1").selection("sel5").set(2, 6, 10, 14, 18, 22);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u8d1f\u6781\u7ec8\u7aef");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(9);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u6b63\u6781\u7ec8\u7aef");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(60);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u5916\u90e8\u7535\u89e3\u8d28");
    model.component("comp1").selection("sel8").set(1, 26);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5377\u7ed5\u5f0f\u7535\u6c60");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3", "sel4", "sel5"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u6b63\u6781 + \u7535\u89e3\u8d28 + \u8d1f\u6781");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel3", "sel4", "sel5", "sel8"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat3").label("LiPF6 in 1:2 EC:DMC and p(VdF-HFP) (Polymer, Li-ion Battery)");
    model.component("comp1").material("mat3").comments("\n");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"7.5e-11[m^2/s]", "0", "0", "0", "7.5e-11[m^2/s]", "0", "0", "0", "7.5e-11[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "0.0108"}, 
         {"0.2000", "0.1259"}, 
         {"0.4000", "0.2055"}, 
         {"0.6000", "0.2553"}, 
         {"0.8000", "0.2810"}, 
         {"1.0000", "0.2873"}, 
         {"1.2000", "0.2788"}, 
         {"1.4000", "0.2595"}, 
         {"1.6000", "0.2331"}, 
         {"1.8000", "0.2027"}, 
         {"2.0000", "0.1710"}, 
         {"2.200", "0.1403"}, 
         {"2.4000", "0.1123"}, 
         {"2.6000", "0.0885"}, 
         {"2.8000", "0.0696"}, 
         {"3.0000", "0.0563"}});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/c_ref)", "0", "0", "0", "sigmal_int1(c/c_ref)", "0", "0", "0", "sigmal_int1(c/c_ref)"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").set("c_ref", "1000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").descr("c_ref", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").set("transpNum", "0.363");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").set("fcl", "0");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .setPropertyInfo("cElsalt", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat4").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat4").label("Graphite, LixC6 MCMB (Negative, Li-ion Battery)");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("fununit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("funcname", "Eeq");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "2.781186612"}, 
         {"0.01", "1.520893224"}, 
         {"0.02", "0.893922607"}, 
         {"0.03", "0.581284406"}, 
         {"0.04", "0.42452844"}, 
         {"0.05", "0.344895805"}, 
         {"0.06", "0.303146342"}, 
         {"0.07", "0.279578072"}, 
         {"0.08", "0.264093089"}, 
         {"0.09", "0.251347845"}, 
         {"0.1", "0.238588379"}, 
         {"0.11", "0.224803164"}, 
         {"0.12", "0.210294358"}, 
         {"0.13", "0.196408586"}, 
         {"0.14", "0.184624188"}, 
         {"0.15", "0.175188157"}, 
         {"0.16", "0.167373311"}, 
         {"0.17", "0.160452107"}, 
         {"0.18", "0.154025412"}, 
         {"0.19", "0.147948522"}, 
         {"0.2", "0.142214997"}, 
         {"0.21", "0.13688271"}, 
         {"0.22", "0.132033114"}, 
         {"0.23", "0.127747573"}, 
         {"0.24", "0.124091616"}, 
         {"0.25", "0.121103387"}, 
         {"0.26", "0.11878567"}, 
         {"0.27", "0.117102317"}, 
         {"0.28", "0.115980205"}, 
         {"0.29", "0.115317054"}, 
         {"0.3", "0.114993965"}, 
         {"0.31", "0.114890105"}, 
         {"0.32", "0.114886278"}, 
         {"0.33", "0.114884619"}, 
         {"0.34", "0.114873068"}, 
         {"0.35", "0.114824904"}, 
         {"0.36", "0.114644725"}, 
         {"0.37", "0.114372614"}, 
         {"0.38", "0.114017954"}, 
         {"0.39", "0.11359371"}, 
         {"0.4", "0.11311133"}, 
         {"0.41", "0.112575849"}, 
         {"0.42", "0.111980245"}, 
         {"0.43", "0.111297682"}, 
         {"0.44", "0.110470149"}, 
         {"0.45", "0.109393081"}, 
         {"0.46", "0.107900592"}, 
         {"0.47", "0.10576964"}, 
         {"0.48", "0.102783317"}, 
         {"0.49", "0.09889031"}, 
         {"0.5", "0.094391564"}, 
         {"0.51", "0.089921069"}, 
         {"0.52", "0.086112415"}, 
         {"0.53", "0.083265315"}, 
         {"0.54", "0.081326247"}, 
         {"0.55", "0.080074892"}, 
         {"0.56", "0.07928329"}, 
         {"0.57", "0.078778765"}, 
         {"0.58", "0.078447703"}, 
         {"0.59", "0.078220432"}, 
         {"0.6", "0.078055641"}, 
         {"0.61", "0.077929111"}, 
         {"0.62", "0.077826563"}, 
         {"0.63", "0.077739397"}, 
         {"0.64", "0.077662227"}, 
         {"0.65", "0.077591472"}, 
         {"0.66", "0.077524557"}, 
         {"0.67", "0.077459463"}, 
         {"0.68", "0.077394455"}, 
         {"0.69", "0.077327934"}, 
         {"0.7", "0.077258337"}, 
         {"0.71", "0.077184077"}, 
         {"0.72", "0.077103499"}, 
         {"0.73", "0.077014851"}, 
         {"0.74", "0.076916258"}, 
         {"0.75", "0.07680571"}, 
         {"0.76", "0.07668104"}, 
         {"0.77", "0.07653992"}, 
         {"0.78", "0.076379839"}, 
         {"0.79", "0.076198086"}, 
         {"0.8", "0.075991699"}, 
         {"0.81", "0.075757371"}, 
         {"0.82", "0.075491288"}, 
         {"0.83", "0.075188813"}, 
         {"0.84", "0.07484398"}, 
         {"0.85", "0.074448647"}, 
         {"0.86", "0.07399118"}, 
         {"0.87", "0.073454466"}, 
         {"0.88", "0.072812991"}, 
         {"0.89", "0.072028722"}, 
         {"0.9", "0.071045433"}, 
         {"0.91", "0.069780996"}, 
         {"0.92", "0.068116222"}, 
         {"0.93", "0.065874599"}, 
         {"0.94", "0.062770873"}, 
         {"0.95", "0.058253898"}, 
         {"0.96", "0.051075794"}, 
         {"0.97", "0.038790069"}, 
         {"0.98", "0.020172191"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("defineinv", true);
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("funcname", "dEeqdT");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"0", "3.0e-4"}, 
         {"0.17", "0"}, 
         {"0.24", "-6e-5"}, 
         {"0.28", "-1.6e-4"}, 
         {"0.5", "-1.6e-4"}, 
         {"0.54", "-9e-5"}, 
         {"0.71", "-9e-5"}, 
         {"0.85", "-1.0e-4"}, 
         {"1.0", "-1.2e-4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "E_int(c/csmax)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "nu_int(c/csmax)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"100[S/m]", "0", "0", "0", "100[S/m]", "0", "0", "0", "100[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "V. Srinivasan, and J. Newman, \u201cDesign and Optimization of a Natural Graphite/Iron Phosphate Lithium Ion Cell,\u201d J. Electrochem. Soc., vol. 151, p. 1530, 2004.");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("diffusion", new String[]{"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("diffusion", "K. Kumaresan, G. Sikha, and R. E. White, \u201cThermal Model for a Li-Ion Cell,\u201d J. Electrochem. Soc., vol. 155, p. A164, 2008.");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "S. Chen, C. Wan, and Y. Wang, J. Power Sources, 140, 111 (2005).");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "750[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("density", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat4").propertyGroup("def").set("csmax", "31507[mol/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T_ref", "318[K]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "D. P Karthikeyan, G. Sikha, and R. E. White, \u201cThermodynamic model development for lithium intercalation electrodes,\u201d J. Power Sources, vol. 185, p. 1398, 2008.");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
    model.component("comp1").material("mat4").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"0.006802721088435382", "0.12500000000000178"}, 
         {"0.06316812439261421", "1.2736486486486491"}, 
         {"0.11175898931000966", "2.523648648648649"}, 
         {"0.17978620019436342", "3.5709459459459474"}, 
         {"0.2400388726919339", "4.449324324324325"}, 
         {"0.2905733722060252", "5.192567567567568"}, 
         {"0.3566569484936831", "5.66554054054054"}, 
         {"0.4188532555879494", "5.969594594594595"}, 
         {"0.48104956268221566", "6.10472972972973"}, 
         {"0.5432458697764819", "6.173648648648647"}, 
         {"0.58600583090379", "6.306081081081081"}, 
         {"0.6112730806608356", "7.726351351351352"}, 
         {"0.6443148688046647", "8.570945945945946"}, 
         {"0.694849368318756", "9.449324324324323"}, 
         {"0.7414965986394557", "10.29391891891892"}, 
         {"0.7764820213799805", "10.902027027027025"}, 
         {"0.8231292517006802", "11.543918918918918"}, 
         {"0.8542274052478133", "12.152027027027026"}, 
         {"0.8833819241982507", "12.827702702702702"}, 
         {"0.9183673469387755", "12.996621621621621"}, 
         {"0.9494655004859086", "13.16554054054054"}});
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat4").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat4").propertyGroup("ic")
         .setPropertyInfo("dvol", "S. Schweidler, L. de Biasi, A. Schiele, P. Hartmann, T. Brezesinski and J. Janek, \"Volume Changes of Graphite Anodes Revisited: A Combined Operando X-Ray Diffraction and In Situ Pressure Analysis Study\", J. Phys. Chem. C, 2018, 122, 8829\u20138835");
    model.component("comp1").material("mat4").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat5").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat5").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat5").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat5").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat5").label("LMO, LiMn2O4 Spinel (Positive, Li-ion Battery)");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.1750", "4.2763"}, 
         {"0.1950", "4.1898"}, 
         {"0.2150", "4.1507"}, 
         {"0.2350", "4.133"}, 
         {"0.2550", "4.1248"}, 
         {"0.2750", "4.1209"}, 
         {"0.2950", "4.119"}, 
         {"0.3150", "4.1179"}, 
         {"0.3350", "4.1171"}, 
         {"0.3550", "4.1165"}, 
         {"0.3750", "4.116"}, 
         {"0.3950", "4.1153"}, 
         {"0.4150", "4.1145"}, 
         {"0.4350", "4.1135"}, 
         {"0.4550", "4.1121"}, 
         {"0.4750", "4.1099"}, 
         {"0.4950", "4.1066"}, 
         {"0.5150", "4.1014"}, 
         {"0.5350", "4.0934"}, 
         {"0.5550", "4.082"}, 
         {"0.5750", "4.067"}, 
         {"0.5950", "4.05"}, 
         {"0.6150", "4.0333"}, 
         {"0.6350", "4.0192"}, 
         {"0.6550", "4.0087"}, 
         {"0.6750", "4.0012"}, 
         {"0.6950", "3.996"}, 
         {"0.7150", "3.9923"}, 
         {"0.7350", "3.9893"}, 
         {"0.7550", "3.9867"}, 
         {"0.7750", "3.9841"}, 
         {"0.7950", "3.9813"}, 
         {"0.8150", "3.9783"}, 
         {"0.8350", "3.9747"}, 
         {"0.8550", "3.9705"}, 
         {"0.8750", "3.9652"}, 
         {"0.8950", "3.9585"}, 
         {"0.9150", "3.9493"}, 
         {"0.9350", "3.9361"}, 
         {"0.9550", "3.9144"}, 
         {"0.9750", "3.869"}, 
         {"0.9950", "3.5944"}, 
         {"", ""}});
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.15", "0.15e-3"}, 
         {"0.18", "0.25e-3"}, 
         {"0.20", "0.21e-3"}, 
         {"0.209", "0.19e-3"}, 
         {"0.26", "0.175e-3"}, 
         {"0.28", "0.166e-3"}, 
         {"0.30", "0.155e-3"}, 
         {"0.35", "0.11e-3"}, 
         {"0.394", "0.095e-3"}, 
         {"0.41", "0.05e-3"}, 
         {"0.437", "0.02e-3"}, 
         {"0.445", "0"}, 
         {"0.46", "-0.048e-3"}, 
         {"0.48", "-0.15e-3"}, 
         {"0.50", "-0.255e-3"}, 
         {"0.515", "-0.3e-3"}, 
         {"0.545", "-0.3e-3"}, 
         {"0.553", "-0.22e-3"}, 
         {"0.58", "-0.145e-3"}, 
         {"0.592", "-0.10e-3"}, 
         {"0.60", "0"}, 
         {"0.62", "0.08e-3"}, 
         {"0.64", "0.12e-3"}, 
         {"0.70", "0.124e-3"}, 
         {"0.72", "0.10e-3"}, 
         {"0.73", "0.05e-3"}, 
         {"0.76", "0"}, 
         {"0.78", "-0.057e-3"}, 
         {"0.81", "-0.08e-3"}, 
         {"0.86", "-0.10e-3"}, 
         {"0.91", "-0.16e-3"}, 
         {"0.96", "-0.22e-3"}, 
         {"0.98", "-0.30e-3"}});
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "194[GPa]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.26");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrochemical and Electronic Charge Transport Properties of Ni-Doped LiMn2O4 Spinel Obtained from Polyol-Mediated Synthesis, Shuo Yang et al, Materials 2018, 11(5), 806");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "4140[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat5").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat5").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat5").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat5").propertyGroup("def").set("csmax", "22860[mol/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat5").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat5").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat5").propertyGroup("OperationalSOC").set("E_min", "3.9[V]");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat5").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").selection().named("uni2");

    model.component("comp1").physics("liion").feature("sep1").set("epsl", 1);
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel4");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat4");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs0_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Nel", 3);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat4");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphaa", "aA_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphac", "aC_neg");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 2);
    model.component("comp1").physics("liion").feature("pce2").selection().named("sel3");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat5");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("csinit", "cs0_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Nel", 5);
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat5");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("alphaa", "aA_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("alphac", "aC_pos");
    model.component("comp1").physics("liion").create("cc1", "CurrentConductor", 2);
    model.component("comp1").physics("liion").feature("cc1").selection().named("sel2");
    model.component("comp1").physics("liion").create("cc2", "CurrentConductor", 2);
    model.component("comp1").physics("liion").feature("cc2").selection().named("sel1");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("liion").feature("egnd1").selection().named("sel6");
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 1);
    model.component("comp1").physics("liion").feature("ecd1")
         .label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 - 1C \u653e\u7535");
    model.component("comp1").physics("liion").feature("ecd1").selection().named("sel7");
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "i_app_1C");
    model.component("comp1").physics("liion").feature().duplicate("ecd2", "ecd1");
    model.component("comp1").physics("liion").feature("ecd2")
         .label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 - 10C \u653e\u7535");
    model.component("comp1").physics("liion").feature("ecd2").set("nis", "10*i_app_1C");
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotential", 1);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(9, 17);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(5, 13);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("edg3", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg3").selection().set(11, 19);
    model.component("comp1").mesh("mesh1").feature("edg3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("edg4", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg4").selection().set(7, 15);
    model.component("comp1").mesh("mesh1").feature("edg4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").create("edg5", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg5").selection()
         .set(6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54);
    model.component("comp1").mesh("mesh1").feature("edg5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1")
         .set("explicit", "0 5e-5 10^range(-4,0.25,-2) range(2e-2,1e-2,0.98) (1-10^range(-2,-0.25,-4)) 0.99995 1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").label("\u7535\u6c60\u7535\u538b\u63a2\u9488 - \u7814\u7a76 1");
    model.component("comp1").probe("bnd1").selection().named("sel7");
    model.component("comp1").probe("bnd1").set("expr", "phis");
    model.component("comp1").probe("bnd1").set("descr", "\u7535\u52bf");

    model.study("std1").feature("time").set("tlist", "range(0,300,1800)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"liion/ecd2"});
    model.study("std1").label("\u7814\u7a76 1 - 1 C \u653e\u7535");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"liion.soc_average_pce1", "liion.soc_average_pce2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 2"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"liion.Ilx", "liion.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (liion)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"liion.Ilx", "liion.Ily"});
    model.result("pg4").feature("arws1").set("arrowbase", "center");
    model.result("pg4").feature("arws1").set("color", "gray");
    model.result("pg4").feature("arws1").create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "root.comp1.liion.IlMag");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 7, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phis"});
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"liion.Isx", "liion.Isy"});
    model.result("pg5").feature("arws1").set("arrowbase", "center");
    model.result("pg5").feature("arws1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 7, 0);
    model.result("pg6").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (liion)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"liion.Isx", "liion.Isy"});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("color", "gray");
    model.result("pg6").feature("arws1").create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("expr", "root.comp1.liion.IsMag");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cl"});
    model.result("pg7").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"liion.Nposx", "liion.Nposy"});
    model.result("pg7").feature("arws1").set("color", "red");
    model.result("pg7").create("arws2", "ArrowSurface");
    model.result("pg7").feature("arws2").set("expr", new String[]{"liion.Nnegx", "liion.Nnegy"});
    model.result("pg7").feature("arws2").set("color", "black");
    model.result("pg7").feature("arws2").set("inheritplot", "arws1");
    model.result("pg7").feature("arws2").set("inheritcolor", "off");
    model.result("pg2").run();
    model.result("pg7").run();
    model.result("pg7").set("titletype", "label");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("hght1", "Height");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7535\u538b - \u7814\u7a76 1");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().named("sel4");
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").selection().geom("geom1", 2);
    model.result().dataset("dset5").selection().named("sel3");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u8d1f\u6781\u9897\u7c92\u8868\u9762\u7684\u76f8\u5bf9\u9502\u6d53\u5ea6");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "liion.socloc_surface");
    model.result("pg8").feature("surf1")
         .set("descr", "\u5c40\u90e8\u7535\u6781\u6750\u6599\u8377\u7535\u72b6\u6001\uff0c\u9897\u7c92\u8868\u9762");
    model.result("pg8").feature("surf1").create("hght1", "Height");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u6b63\u6781\u9897\u7c92\u8868\u9762\u7684\u76f8\u5bf9\u9502\u6d53\u5ea6");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "liion.socloc_surface");
    model.result("pg9").feature("surf1")
         .set("descr", "\u5c40\u90e8\u7535\u6781\u6750\u6599\u8377\u7535\u72b6\u6001\uff0c\u9897\u7c92\u8868\u9762");
    model.result("pg9").feature("surf1").create("hght1", "Height");
    model.result("pg9").run();
    model.result("pg9").run();

    model.component("comp1").probe().duplicate("bnd2", "bnd1");
    model.component("comp1").probe("bnd2").label("\u7535\u6c60\u7535\u538b\u63a2\u9488 - \u7814\u7a76 2");
    model.component("comp1").probe("bnd2").set("table", "new");
    model.component("comp1").probe("bnd2").set("window", "new");

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").set("tlist", "range(0,30,90) 105");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"bnd2"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"liion/ecd1"});
    model.study("std2").label("\u7814\u7a76 2 - 10 C \u653e\u7535");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("bnd2").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg3").run();
    model.result("pg3").set("data", "dset6");
    model.result("pg3").run();
    model.result("pg10").set("window", "window2");
    model.result("pg10").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg10").run();
    model.result("pg10").label("\u7535\u6c60\u7535\u538b - \u7814\u7a76 2");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset6");
    model.result("pg7").run();
    model.result().dataset("dset4").set("solution", "sol3");
    model.result().dataset("dset5").set("solution", "sol3");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").run();

    model.component("comp1").probe().duplicate("bnd3", "bnd2");
    model.component("comp1").probe("bnd3").label("\u7535\u6c60\u7535\u538b\u63a2\u9488 - \u7814\u7a76 3");
    model.component("comp1").probe("bnd3").set("table", "new");
    model.component("comp1").probe("bnd3").set("window", "new");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/liion", true);
    model.study("std3").feature("time").set("tlist", "0 10^range(-1,1,4)");
    model.study("std3").feature("time").set("probesel", "manual");
    model.study("std3").feature("time").set("probes", new String[]{"bnd3"});
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"liion/ecd1", "liion/ecd2"});
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std1");
    model.study("std3").label("\u7814\u7a76 3 - 1C \u653e\u7535\u540e\u7684\u677e\u5f1b");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("bnd3").genResult("none");

    model.sol("sol5").runAll();

    model.result().dataset("dset4").set("solution", "sol5");
    model.result().dataset("dset5").set("solution", "sol5");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset8");
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();
    model.result("pg11").set("window", "window3");
    model.result("pg11").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg11").run();
    model.result("pg11").label("\u7535\u6c60\u7535\u538b - \u7814\u7a76 3");
    model.result("pg11").set("legendpos", "lowerright");
    model.result("pg11").set("window", "window3");
    model.result("pg11").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg11").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "dset8");
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 7, 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 7, 0);
    model.result("pg9").run();

    model.study("std1").feature("time").set("probesel", "manual");
    model.study("std1").feature("time").set("probes", new String[]{"bnd1"});
    model.study("std2").feature("time").set("probes", new String[]{"bnd2"});

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().move("pg1", 8);

    model.title("\u5377\u7ed5\u5f0f\u9502\u79bb\u5b50\u7535\u6c60\u7684\u8fb9\u7f18\u6548\u5e94");

    model
         .description("\u8fd9\u4e2a\u4e8c\u7ef4\u6a21\u578b\u7814\u7a76\u5377\u7ed5\u5f0f\uff08\u201c\u5706\u67f1\u5377\u7ed5\u5f0f\u201d\uff09\u9502\u79bb\u5b50\u7535\u6c60\u4e2d\u7684\u51e0\u4f55\u8fb9\u7f18\u6548\u5e94\uff0c\u5176\u4e2d\u91c7\u7528\u7b49\u6e29\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("li_battery_spiral_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
