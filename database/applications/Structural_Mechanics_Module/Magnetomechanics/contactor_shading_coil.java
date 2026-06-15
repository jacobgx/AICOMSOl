/*
 * contactor_shading_coil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:11 by COMSOL 6.3.0.290. */
public class contactor_shading_coil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Magnetomechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid");
    model.component("comp1").physics("mf").feature("als1").selection().all();

    model.component("comp1").multiphysics().create("mmcpl1", "Magnetomechanics", 2);
    model.component("comp1").multiphysics("mmcpl1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("mmcpl1").set("MagneticFields_physics", "mf");
    model.component("comp1").multiphysics("mmcpl1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/mf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mmcpl1", true);

    model.param().set("em_w", "100[mm]");
    model.param().descr("em_w", "\u5bbd\u5ea6\uff0c\u7535\u78c1\u4f53");
    model.param().set("em_h", "60[mm]");
    model.param().descr("em_h", "\u9ad8\u5ea6\uff0c\u7535\u78c1\u4f53");
    model.param().set("core_w", "20[mm]");
    model.param().descr("core_w", "\u5bbd\u5ea6\uff0c\u94c1\u82af");
    model.param().set("pl_h", "30[mm]");
    model.param().descr("pl_h", "\u9ad8\u5ea6\uff0c\u94c1\u67f1\u585e");
    model.param().set("plunger_travel", "6[mm]");
    model.param().descr("plunger_travel", "\u603b\u51b2\u7a0b\uff0c\u94c1\u67f1\u585e");
    model.param().set("N", "500");
    model.param().descr("N", "\u531d\u6570\uff0c\u521d\u7ea7\u7ebf\u5708");
    model.param().set("d", "1.35[N*s/m]");
    model.param().descr("d", "\u963b\u5c3c\u5e38\u6570\uff0c\u56de\u4f4d\u5f39\u7c27");
    model.param().set("kr", "24.2[N/m]");
    model.param().descr("kr", "\u5f39\u7c27\u5e38\u6570\uff0c\u56de\u4f4d\u5f39\u7c27");
    model.param().set("l0", "plunger_travel*0.1");
    model.param().descr("l0", "\u672a\u53d8\u5f62\u65f6\u7684\u957f\u5ea6\uff0c\u63a5\u89e6\u5f39\u7c27");
    model.param().set("op", "plunger_travel*0.7");
    model.param().descr("op", "\u6253\u5f00\uff0c\u7535\u89e6\u5934");
    model.param().set("dc", "2.7[mm]");
    model.param().descr("dc", "\u76f4\u5f84\uff0c\u5c4f\u853d\u7ebf\u5708");
    model.param().set("kc", "255[N/m]");
    model.param().descr("kc", "\u5f39\u7c27\u5e38\u6570\uff0c\u63a5\u89e6\u5f39\u7c27");
    model.param().set("c", "5[N*s/m]");
    model.param().descr("c", "\u963b\u5c3c\u7cfb\u6570\uff0c\u63a5\u89e6\u5f39\u7c27");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"em_w/2", "em_h"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"em_w/2", "pl_h"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "em_h"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"(em_w-2*core_w)/2", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "em_h+pl_h -core_w", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"core_w/2", "core_w/2"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{20, 44});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{12, 12});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new int[]{50, 5});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{0, 90});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{10, 20});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{0, 95});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new int[]{24, 3});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new int[]{0, 115});
    model.component("comp1").geom("geom1").feature("r7").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r7").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r7").setIndex("layer", 10, 0);
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new int[]{6, 1});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new int[]{18, 114});
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new int[]{6, 1});
    model.component("comp1").geom("geom1").feature("r9").set("pos", new int[]{18, 113});
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("r10").set("size", new int[]{40, 3});
    model.component("comp1").geom("geom1").feature("r10").set("pos", new int[]{18, 110});
    model.component("comp1").geom("geom1").run("r10");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "l0");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("r10", "r7", "r8", "r9");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").set("disply", "plunger_travel");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("mov1", "r5", "r6", "spl1(2)");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("mov3", "Move");
    model.component("comp1").geom("geom1").feature("mov3").set("disply", "-op");
    model.component("comp1").geom("geom1").feature("mov3").selection("input").set("mov2(1)", "mov2(4)");
    model.component("comp1").geom("geom1").run("mov3");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "core_w*0.1");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("mov2(7)", 3, 5, 7);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("spl1(1)", 4, 6, 8);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").set("radius", 1);
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("mov2(3)", 1, 2);
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("mov3(2)", 3, 4);
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("r11").set("size", new String[]{"3*em_w/2", "3*em_w"});
    model.component("comp1").geom("geom1").feature("r11").set("pos", new String[]{"0", "-em_w"});
    model.component("comp1").geom("geom1").run("r11");
    model.component("comp1").geom("geom1").create("r12", "Rectangle");
    model.component("comp1").geom("geom1").feature("r12").set("size", new String[]{"dc", "2"});
    model.component("comp1").geom("geom1").feature("r12").set("base", "center");
    model.component("comp1").geom("geom1").feature("r12").set("pos", new int[]{44, 59});
    model.component("comp1").geom("geom1").feature("r12").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r12").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r12").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r12").setIndex("layer", 0.8, 0);
    model.component("comp1").geom("geom1").run("r12");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("r12", 5);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("r13", "Rectangle");
    model.component("comp1").geom("geom1").feature("r13").set("size", new int[]{58, 90});
    model.component("comp1").geom("geom1").feature("r13").set("pos", new int[]{0, 50});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").pair().create("p1", "Contact");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("p1").source().set(44, 78, 80);
    model.component("comp1").pair("p1").destination().set(45, 79, 81);
    model.component("comp1").pair("p1").searchMethod("fast");
    model.component("comp1").pair("p1").manualDist(true);
    model.component("comp1").pair("p1").searchDist("6");
    model.component("comp1").pair().create("p2", "Contact");
    model.component("comp1").pair("p2").source().set(8, 55, 59, 61, 64, 66, 76, 82, 84);
    model.component("comp1").pair("p2").destination().set(10, 56, 77, 83, 85);
    model.component("comp1").pair("p2").searchMethod("fast");
    model.component("comp1").pair("p2").manualDist(true);
    model.component("comp1").pair("p2").searchDist("6");

    model.component("comp1").common("free1").selection().set(4);
    model.component("comp1").common("sym1").selection().set(7, 15, 19, 72, 74);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat1").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat1").set("family", "iron");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").selection().set(2, 3, 5, 16);
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
    model.component("comp1").material("mat2").selection().set(8, 10, 13, 14, 15);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Acrylic plastic");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("lighting", "phong");
    model.component("comp1").material("mat3").set("shininess", 1000);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").selection().set(6, 7);
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
    model.component("comp1").material("mat4").selection().set(11, 12, 17, 18);

    model.component("comp1").physics("solid").prop("d").set("d", "30[mm]");
    model.component("comp1").physics("solid").selection().set(2, 3, 5, 6, 7, 8, 10, 13, 14, 15, 16, 17, 18);
    model.component("comp1").physics("solid").prop("AdvancedSettings").set("GroupPhysOdesAtt", false);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(4, 41, 73);
    model.component("comp1").physics("solid").create("spf1", "SpringFoundation2", 2);
    model.component("comp1").physics("solid").feature("spf1").selection().set(5);
    model.component("comp1").physics("solid").feature("spf1").set("SpringType", "kTot");
    model.component("comp1").physics("solid").feature("spf1")
         .set("kTot", new String[]{"kr", "0", "0", "0", "kr", "0", "0", "0", "kr"});
    model.component("comp1").physics("solid").feature("spf1").set("ViscousType", "DampTot");
    model.component("comp1").physics("solid").feature("spf1")
         .set("DampTot", new String[]{"d", "0", "0", "0", "d", "0", "0", "0", "d"});
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(47, 67, 68, 70, 71);
    model.component("comp1").physics("solid").create("att1", "Attachment", 1);
    model.component("comp1").physics("solid").feature("att1").selection().set(18);
    model.component("comp1").physics("solid").create("att2", "Attachment", 1);
    model.component("comp1").physics("solid").feature("att2").selection().set(16);
    model.component("comp1").physics("solid").create("spd1", "SpringDamper", -1);
    model.component("comp1").physics("solid").feature("spd1").set("Source", "att1");
    model.component("comp1").physics("solid").feature("spd1").set("Destination", "att2");
    model.component("comp1").physics("solid").feature("spd1").set("k", "kc");
    model.component("comp1").physics("solid").feature("spd1").set("c", "c");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(3, 5, 9, 11, 13, 17);
    model.component("comp1").physics("solid").feature("dcnt1").set("destination_offset", "0.05[mm]");
    model.component("comp1").physics("mf").feature("als1").selection().set(6, 7, 8, 10, 13, 14, 15);
    model.component("comp1").physics("mf").prop("d").set("d", "30[mm]");
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").label("\u5b89\u57f9\u5b9a\u5f8b 2\uff0c\u94c1\u82af");
    model.component("comp1").physics("mf").feature("als2").selection().set(2, 3, 5, 16);
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").label("\u7ebf\u5708 1\uff0c\u521d\u7ea7");
    model.component("comp1").physics("mf").feature("coil1").selection().set(11, 12);
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "48[V]*sin(2*pi*50[Hz]*t)/2");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "Diameter");
    model.component("comp1").physics("mf").feature("coil1").set("coilWindDiameter", "1.5[mm]");
    model.component("comp1").physics("mf").create("coil2", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil2").label("\u7ebf\u5708 2\uff0c\u5c4f\u853d");
    model.component("comp1").physics("mf").feature("coil2").selection().set(17, 18);
    model.component("comp1").physics("mf").feature("coil2").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "0[A]");
    model.component("comp1").physics("mf").create("mi2", "MagneticInsulation", 1);
    model.component("comp1").physics("mf").feature("mi2").selection()
         .set(18, 20, 33, 34, 40, 41, 44, 45, 46, 47, 73, 78, 79, 80, 81);
    model.component("comp1").physics("mf").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("mf").feature("symp1").selection().set(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21);

    model.component("comp1").multiphysics("mmcpl1").selection().set(2, 3, 5, 16, 17, 18);

    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"3.5"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"204e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.39"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7840"});

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").label("\u6c14\u9699\uff0c\u7535\u89e6\u5934");
    model.component("comp1").probe("point1").set("probename", "gap_p1");
    model.component("comp1").probe("point1").selection().set(30);
    model.component("comp1").probe("point1").set("expr", "solid.gap_p1");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "\u7535\u89e6\u5934\u6c14\u9699");
    model.component("comp1").probe("point1").set("window", "window1");
    model.component("comp1").probe("point1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").label("\u6c14\u9699\uff0c\u4e2d\u5fc3\u67f1");
    model.component("comp1").probe("point2").set("probename", "gap_p2");
    model.component("comp1").probe("point2").selection().set(4);
    model.component("comp1").probe("point2").set("expr", "solid.gap_p2");
    model.component("comp1").probe("point2").set("descr", "\u4e2d\u5fc3\u67f1\u6c14\u9699");
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u63a5\u89e6\u529b\uff0c\u7535\u89e6\u5934");
    model.component("comp1").probe("var1").set("probename", "T_toty_p1");
    model.component("comp1").probe("var1")
         .set("expr", "solid.dcnt1.T_toty_p1*(solid.dcnt1.T_toty_p1<500[N])+500[N]*(solid.dcnt1.T_toty_p1>500[N])");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u63a5\u89e6\u529b\uff0c\u7535\u89e6\u5934");
    model.component("comp1").probe("var1").set("window", "window2");
    model.component("comp1").probe("var1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2").label("\u63a5\u89e6\u529b\uff0c\u4e2d\u5fc3\u67f1");
    model.component("comp1").probe("var2").set("probename", "T_toty_p2");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").probe("var2").set("descr", "\u63a5\u89e6\u529b\uff0c\u4e2d\u5fc3\u67f1");
    model.component("comp1").probe("var2")
         .set("expr", "solid.dcnt1.T_toty_p2*(solid.dcnt1.T_toty_p2<500[N])+500[N]*(solid.dcnt1.T_toty_p2>500[N])");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(18);

    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").set("probename", "I_coil");
    model.component("comp1").probe("var3").label("\u603b\u7535\u6d41\uff0c\u7ebf\u5708");
    model.component("comp1").probe("var3").set("expr", "mf.ICoil_1");
    model.component("comp1").probe("var3").set("descr", "\u7ebf\u5708\u7535\u6d41");
    model.component("comp1").probe("var3").set("window", "window3");
    model.component("comp1").probe("var3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.component("comp1").probe().duplicate("var4", "var3");
    model.component("comp1").probe("var4").set("probename", "I_shading_coil");
    model.component("comp1").probe("var4").label("\u603b\u7535\u6d41\uff0c\u5c4f\u853d\u7ebf\u5708");
    model.component("comp1").probe("var4").set("expr", "intop1(abs(mf.Jz))");
    model.component("comp1").probe("var4").set("descractive", true);
    model.component("comp1").probe("var4")
         .set("descr", "\u5c4f\u853d\u7ebf\u5708\u7535\u6d41\uff08\u7edd\u5bf9\u503c\uff09");

    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,0.2,20) range(20.5,0.5,40)");
    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol().create("sol2");
    model.sol("sol2").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol2").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol2");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "mf.normB");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("expr", new String[]{"mf.Bx", "mf.By"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.03);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("solutionparams", "parent");
    model.result("pg5").feature("con1").set("expr", "mf.Az");
    model.result("pg5").feature("con1").set("titletype", "none");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("color", "custom");
    model.result("pg5").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg5").feature("con1").set("resolution", "fine");
    model.result("pg5").feature("con1").set("inheritcolor", false);
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").feature("con1").set("inheritplot", "surf1");
    model.result("pg5").feature("con1").feature().create("filt1", "Filter");
    model.result("pg5").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u52a8\u7f51\u683c");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 10, 13, 14, 15, 16, 17, 18);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset2");

    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view1").axis().set("xmin", -90);
    model.component("comp1").view("view1").axis().set("xmax", 90);
    model.component("comp1").view("view1").axis().set("ymin", -20);
    model.component("comp1").view("view1").axis().set("ymax", 160);

    model.result("pg5").run();
    model.result("pg5").set("data", "mir1");
    model.result("pg5").set("view", "view1");

    model.sol("sol1").feature("t1").set("timemethod", "bdf");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", 0.05);

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg5");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u6c14\u9699");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6c14\u9699\uff0c\u7535\u89e6\u5934 (mm)", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6c14\u9699\uff0c\u4e2d\u5fc3\u67f1 (mm)", 1);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u63a5\u89e6\u529b");
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result("pg3").label("\u7ebf\u5708\u7535\u6d41");

    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mf").feature("fcal1")
         .label("\u529b\u8ba1\u7b97\uff0c\u7528\u4e8e\u540e\u5904\u7406");
    model.component("comp1").physics("mf").feature("fcal1").selection().set(5);

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.sol("sol1").updateSolution();
    model.sol("sol2").updateSolution();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").run();
    model.result("pg7").label("\u603b\u7535\u78c1\u529b");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "2*mf.Forcey_0", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u603b\u7535\u78c1\u529b\uff0cy \u5206\u91cf", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u63a5\u89e6\u529b\uff0c\u7535\u89e6\u5934");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevelinput", "interp", 0);
    model.result("pg8").setIndex("interp", "range(15.55,0.5,40)", 0);
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"T_toty_p1"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u63a5\u89e6\u529b\uff0c\u7535\u89e6\u5934"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg8").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{143});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{126});
    model.result("pg5").run();

    model.title("\u5e26\u5c4f\u853d\u7ebf\u5708\u7684\u4ea4\u6d41\u63a5\u89e6\u5668");

    model
         .description("\u4ea4\u6d41\u63a5\u89e6\u5668\u662f\u4e00\u79cd\u7279\u6b8a\u7c7b\u578b\u7684\u78c1\u6027\u5f00\u5173\u88c5\u7f6e\uff0c\u7531\u4ea4\u6d41\u7535\u4f9b\u7535\u7684\u521d\u7ea7\u7ebf\u5708\u6fc0\u6d3b\u3002\u4e0e\u76f4\u6d41\u5f00\u5173\u4e0d\u540c\u7684\u662f\uff0c\u8fd9\u79cd\u88c5\u7f6e\u53ef\u80fd\u4f1a\u5728\u4ea4\u6d41\u7535\u6d41\u8fc7\u96f6\u65f6\u51fa\u73b0\u91cd\u65b0\u5f00\u542f\u7684\u8d8b\u52bf\u3002\u901a\u8fc7\u589e\u52a0\u4e00\u4e2a\u652f\u6301\u76f8\u5bf9\u4e8e\u9988\u7535\u7ebf\u5708\u7684\u5ef6\u8fdf\u611f\u5e94\u7535\u6d41\u7684\u5c4f\u853d\u7ebf\u5708\uff0c\u4f7f\u5b83\u53ef\u4ee5\u59cb\u7ec8\u5177\u6709\u975e\u96f6\u62c9\u529b\uff0c\u4ece\u800c\u63d0\u4f9b\u66f4\u7a33\u5b9a\u7684\u95ed\u5408\u3002\n\n\u672c\u4f8b\u7814\u7a76\u4ea4\u6d41\u63a5\u89e6\u5668\u7684\u95ed\u5408\u52a8\u529b\u5b66\u4ee5\u53ca\u968f\u540e\u5efa\u7acb\u7684\u673a\u68b0\u63a5\u89e6\u3002\u8be5\u88c5\u7f6e\u7684\u8fd0\u52a8\u90e8\u4ef6\u548c\u9759\u6b62\u90e8\u4ef6\u4e4b\u95f4\u7684\u8ddd\u79bb\u53d8\u5316\u4f1a\u5f71\u54cd\u78c1\u573a\u7684\u5206\u5e03\u3002\u672c\u4f8b\u5728\u5176\u5468\u56f4\u7a7a\u6c14\u4e2d\u4f7f\u7528\u52a8\u7f51\u683c\u6765\u89e3\u91ca\u8fd9\u79cd\u5f71\u54cd\uff0c\u5e76\u4f7f\u7528\u201c\u78c1\u529b\u5b66\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u6765\u5efa\u7acb\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("contactor_shading_coil.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
