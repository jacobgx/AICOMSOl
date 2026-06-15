/*
 * stress_corrosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:45 by COMSOL 6.3.0.290. */
public class stress_corrosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2", "19.1[mm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new String[]{"100[mm]", "11.46[mm]"});
    model.component("comp1").geom("geom1").feature("e1").set("pos", new String[]{"1", "19.1[mm]"});
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("e1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{2, 1});
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("disp", "0.004[m]", "\u4f4d\u79fb");
    model.param()
         .set("Eeq0a", "-0.859[V]", "\u65e0\u5e94\u529b\u4f5c\u7528\u4e0b\u94c1\u6eb6\u89e3\u76f8\u5bf9\u4e8e SCE \u7684\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("Eeq0c", "-0.644[V]", "\u65e0\u5e94\u529b\u4f5c\u7528\u4e0b\u6790\u6c22\u76f8\u5bf9\u4e8e SCE \u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0a", "2.353e-3[A/m^2]", "\u94c1\u6eb6\u89e3\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("ba", "118[mV]", "\u94c1\u6eb6\u89e3\u7684 Tafel \u659c\u7387");
    model.param().set("i0c", "1.457e-2[A/m^2]", "\u6790\u6c22\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("bc", "-207[mV]", "\u6790\u6c22\u7684 Tafel \u659c\u7387");
    model.param().set("deltaPm", "806e6[Pa]/3", "\u5f15\u8d77\u5f39\u6027\u53d8\u5f62\u7684\u8d85\u538b");
    model.param().set("Vm", "7.13e-6[m^3/mol]", "\u94a2\u7684\u6469\u5c14\u4f53\u79ef");
    model.param().set("zm", "2", "\u7535\u8377\u6570");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("nu", "0.45", "\u65b9\u5411\u76f8\u5173\u56e0\u5b50");
    model.param().set("alpha", "1.67e11[1/cm^2]", "\u7cfb\u6570");
    model.param().set("N0", "1e8[1/cm^2]", "\u521d\u59cb\u4f4d\u9519\u5bc6\u5ea6");
    model.param()
         .set("deltaEeqae", "-(deltaPm*Vm/(zm*F_const))", "\u5f39\u6027\u53d8\u5f62\u5f15\u8d77\u7684\u5e73\u8861\u7535\u4f4d\u53d8\u5316");
    model.param().set("sigmal", "0.096[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("sigmas", "1e6[S/m]", "\u7535\u5bfc\u7387");
    model.param().set("rho_Fe", "7.87[g/cm^3]", "\u94c1\u7684\u5bc6\u5ea6");
    model.param().set("M_Fe", "55.845[g/mol]", "\u94c1\u7684\u6469\u5c14\u8d28\u91cf");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("hardening", "max(0,stress_strain_curve(solid.epe+solid.mises/solid.E)-solid.sigmags)", "\u786c\u5316\u51fd\u6570");
    model.component("comp1").variable("var1")
         .set("deltaEeqap", "-T*R_const/(zm*F_const)*log((nu*alpha*solid.epe)/N0+1)", "\u5851\u6027\u53d8\u5f62\u5f15\u8d77\u7684\u9633\u6781\u5e73\u8861\u7535\u4f4d\u53d8\u5316");
    model.component("comp1").variable("var1")
         .set("Eeqa", "Eeq0a+deltaEeqae+deltaEeqap", "\u5305\u542b\u5f39\u6027\u548c\u5851\u6027\u53d8\u5f62\u9879\u7684\u9633\u6781\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("ic", "i0c*10^(-solid.mises*Vm/(6*F_const*bc))", "\u5305\u542b\u5e94\u529b\u56e0\u5b50\u7684\u9634\u6781\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "stress_strain_curve");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0", "100"}, 
         {"0.003", "800"}, 
         {"0.004", "810"}, 
         {"0.01", "830"}, 
         {"0.02", "850"}, 
         {"0.03", "855"}, 
         {"0.04", "860"}, 
         {"0.05", "855"}, 
         {"0.06", "845"}, 
         {"0.07", "830"}, 
         {"0.08", "805"}, 
         {"0.09", "780"}, 
         {"0.1", "750"}, 
         {"0.12", "650"}, 
         {"0.14", "530"}});
    model.component("comp1").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", "MPa", 0);

    model.component("comp1").physics("solid").selection().set(1);
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("High-strength alloy steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.5882352941176471, 0.5882352941176471, 0.5882352941176471});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.12);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-300[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-620[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-720[GPa]");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"806[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"hardening"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"207[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.33"});

    model.component("comp1").physics("solid").feature("init1").set("u", new String[]{"0.0001*X", "0", "0"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(7);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "disp", 0);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().set(1);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("fix2", "Fixed", 0);
    model.component("comp1").physics("solid").feature("fix2").selection().set(1);
    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("cd").feature("init1").set("phil", "-(Eeq0a+Eeq0c)/2");
    model.component("comp1").physics("cd").create("cc1", "CurrentConductor", 2);
    model.component("comp1").physics("cd").feature("cc1").selection().set(1);
    model.component("comp1").physics("cd").feature("cc1").set("sigma_mat", "userdef");
    model.component("comp1").physics("cd").feature("cc1")
         .set("sigma", new String[]{"sigmas", "0", "0", "0", "sigmas", "0", "0", "0", "sigmas"});
    model.component("comp1").physics("cd").create("bei1", "InternalElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("bei1").selection().set(4, 6, 9, 10);
    model.component("comp1").physics("cd").feature("bei1").feature("er1").set("Eeq", "Eeqa");
    model.component("comp1").physics("cd").feature("bei1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cd").feature("bei1").feature("er1").set("i0", "i0a");
    model.component("comp1").physics("cd").feature("bei1").feature("er1").set("Aa", "ba");
    model.component("comp1").physics("cd").feature("bei1").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("cd").feature("bei1").feature("er2").set("Eeq", "Eeq0c");
    model.component("comp1").physics("cd").feature("bei1").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cd").feature("bei1").feature("er2").set("i0", "ic");
    model.component("comp1").physics("cd").feature("bei1").feature("er2").set("Ac", "bc");
    model.component("comp1").physics("cd").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("cd").feature("egnd1").selection().set(1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(9, 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.001);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u7a33\u6001\u53c2\u6570\u5316");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "disp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "disp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.001 0.002 0.003 0.004", 0);
    model.study("std1").feature("stat").setSolveFor("/physics/cd", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat2").set("usestol", true);
    model.study("std1").feature("stat2").set("stol", "0.00001");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u8150\u8680\u7535\u4f4d\u548c von Mises \u5e94\u529b");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "solid.misesGp");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "-phil");
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", -0.733);
    model.result("pg1").feature("surf2").set("rangecolormax", -0.724);
    model.result("pg1").feature("surf2").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("von Mises \u5e94\u529b\uff0c\u53c2\u6570\u5316");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u7f3a\u9677\u957f\u5ea6 (mm)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(9, 10);
    model.result("pg2").feature("lngr1").set("expr", "solid.misesGp");
    model.result("pg2").feature("lngr1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("lngr1").set("unit", "MPa");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("xdataunit", "mm");
    model.result("pg2").feature("lngr1").set("linemarker", "cycle");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg2").feature("lngr1").set("legendpattern", "eval(disp,mm) mm");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u8150\u8680\u7535\u4f4d\uff0c\u53c2\u6570\u5316");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u8150\u8680\u7535\u4f4d (V)");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "cd.Evsref");
    model.result("pg3").feature("lngr1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u9633\u6781\u7535\u6d41\u5bc6\u5ea6\uff0c\u53c2\u6570\u5316");
    model.result("pg4").set("ylabel", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "cd.iloc_er1");
    model.result("pg4").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u9634\u6781\u7535\u6d41\u5bc6\u5ea6\uff0c\u53c2\u6570\u5316");
    model.result("pg5").set("ylabel", "\u9634\u6781\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "cd.iloc_er2");
    model.result("pg5").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");

    model.component("comp1").physics("cd").feature("bei1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("rhos", "rho_Fe", 0, 0);
    model.component("comp1").physics("cd").feature("bei1").setIndex("Ms", "M_Fe", 0, 0);
    model.component("comp1").physics("cd").feature("bei1").feature("er1").setIndex("Vib", 1, 0, 0);

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/desdg1", false);
    model.study("std1").feature("stat2").setSolveFor("/frame/material1", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/ndbdg1", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/desdg1", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/cd", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/desdg1", true);
    model.study("std2").feature("time").set("tunit", "a");
    model.study("std2").feature("time").set("tlist", "range(0,1,20)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("initsol", "sol3");
    model.study("std2").label("\u7814\u7a76\uff1a\u77ac\u6001\uff0c\u53d8\u5f62\u51e0\u4f55");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol8").feature("t1").feature("fc1").set("maxiter", 12);

    model.study("std2").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8150\u8680\u7f3a\u9677\u66f2\u7ebf");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{1, 21}, 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(9, 10);
    model.result("pg6").feature("lngr1").set("expr", "y");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().duplicate("pg7", "pg2");
    model.result("pg7").run();
    model.result("pg7").label("von Mises \u5e94\u529b\uff0c\u53d8\u5f62\u51e0\u4f55");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{1, 21}, 0);
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg7").run();
    model.result("pg3").run();
    model.result().duplicate("pg8", "pg3");
    model.result("pg8").run();
    model.result("pg8").label("\u8150\u8680\u7535\u4f4d\uff0c\u53d8\u5f62\u51e0\u4f55");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{1, 21}, 0);
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg8").run();
    model.result("pg4").run();
    model.result().duplicate("pg9", "pg4");
    model.result("pg9").run();
    model.result("pg9").label("\u9633\u6781\u7535\u6d41\u5bc6\u5ea6\uff0c\u53d8\u5f62\u51e0\u4f55");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevelinput", "manual", 0);
    model.result("pg9").setIndex("looplevel", new int[]{1, 21}, 0);
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg9").run();
    model.result("pg5").run();
    model.result().duplicate("pg10", "pg5");
    model.result("pg10").run();
    model.result("pg10").label("\u9634\u6781\u7535\u6d41\u5bc6\u5ea6\uff0c\u53d8\u5f62\u51e0\u4f55");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", new int[]{1, 21}, 0);
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg10").run();

    model.title("\u5e94\u529b\u8150\u8680");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5f39\u6027\u548c\u5851\u6027\u53d8\u5f62\u5bf9\u7ba1\u9053\u8150\u8680\u7684\u5f71\u54cd\uff0c\u5176\u4e2d\u4f7f\u7528\u542b\u5c0f\u5e94\u53d8\u5851\u6027\u6a21\u578b\u7684\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u8fdb\u884c\u5f39\u5851\u6027\u5e94\u529b\u4eff\u771f\uff0c\u5e76\u4f7f\u7528\u201c\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u201d\u63a5\u53e3\u6765\u6a21\u62df\u7535\u5316\u5b66\u53cd\u5e94\u3002\u7ed3\u679c\u663e\u793a\uff0c\u968f\u7740\u5851\u6027\u53d8\u5f62\u533a\u57df\u7eb5\u5411\u5e94\u53d8\u7684\u589e\u52a0\uff0c\u4f1a\u52a0\u901f\u7ba1\u9053\u7684\u8150\u8680\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("stress_corrosion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
