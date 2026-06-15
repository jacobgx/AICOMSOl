/*
 * oxide_jacking.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:45 by COMSOL 6.3.0.290. */
public class oxide_jacking {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("c");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A_Fe", "0.41[V]", "\u94c1\u6c27\u5316\u7684 Tafel \u659c\u7387");
    model.param().set("A_H2", "-0.15[V]", "\u6790\u6c22\u7684 Tafel \u659c\u7387");
    model.param().set("A_O2", "-0.18[V]", "\u6c27\u8fd8\u539f\u7684 Tafel \u659c\u7387");
    model.param().set("C_O2_ref", "8.6[mol/m^3]", "\u6c27\u53c2\u8003\u6d53\u5ea6");
    model.param().set("Eeq_Fe", "-0.76[V]", "\u94c1\u6c27\u5316\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_H2", "-1.03[V]", "\u6790\u6c22\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_O2", "0.189[V]", "\u6c27\u8fd8\u539f\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Fe", "7.1e-5[A/m^2]", "\u94c1\u6c27\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_H2", "1.1e-2[A/m^2]", "\u6790\u6c22\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_O2", "7.7e-7[A/m^2]", "\u6c27\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_Zn", "-0.68[V]", "Zn \u5e73\u8861\u7535\u4f4d");
    model.param().set("L", "3.175e-2[m]", "\u6df7\u51dd\u571f\u622a\u9762\u957f\u5ea6");
    model.param().set("R_rebar", "0.635e-2[m]", "\u94a2\u7b4b\u534a\u5f84");
    model.param().set("S", "2.54e-2[m]", "\u6df7\u51dd\u571f\u539a\u5ea6");
    model.param().set("W", "6.35e-2[m]", "\u6df7\u51dd\u571f\u622a\u9762\u5bbd\u5ea6");
    model.param().set("PS", "0.6", "\u5b54\u9699\u9971\u548c\u5ea6");
    model.param().set("E_app", "-1[V]", "\u5916\u52a0\u7535\u6c60\u7535\u4f4d");
    model.param().set("r_rebar", "1[cm]/2", "\u94a2\u7b4b\u534a\u5f84");
    model.param().set("rho_oxide", "5.240[g/cm^3]", "\u6c27\u5316\u94c1\u7684\u5bc6\u5ea6");
    model.param().set("M_oxide", "159.688[g/mol]", "\u6c27\u5316\u94c1\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("rho_Fe", "7.87[g/cm^3]", "\u94c1\u7684\u5bc6\u5ea6");
    model.param().set("M_Fe", "55.845[g/mol]", "\u94c1\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("sigmap", "2e6[Pa]", "\u6df7\u51dd\u571f\u7684\u6297\u62c9\u5f3a\u5ea6");
    model.param().set("Gf", "100[J/m^2]", "\u6df7\u51dd\u571f\u7684\u5e94\u53d8\u8f6f\u5316\u8f93\u5165");
    model.param().set("lint", "2e-4[m]", "\u6df7\u51dd\u571f\u7684\u9690\u5f0f\u68af\u5ea6\u957f\u5ea6\u5c3a\u5ea6");
    model.param().set("hdmg", "6e-3[m]", "\u635f\u4f24\u8017\u6563\u533a\u7684\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "10[cm]");
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", 0.05, 0);
    model.component("comp1").geom("geom1").feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_rebar");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"2.5[cm]", "6.5[cm]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 1, 3, 4);
    model.component("comp1").geom("geom1").run("mcd1");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u94a2\u7b4b\u8fb9\u754c\u79ef\u5206");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(5, 6, 7, 8);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("e_init", "intop1(tcd.sbtot)/(2*pi*r_rebar^2)", "Initial mechanical strain from average corrosion layer thickness");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "sigma");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0.2", "0.000175"}, 
         {"0.3", "0.000815"}, 
         {"0.4", "0.002"}, 
         {"0.5", "0.004878"}, 
         {"0.55", "0.005882"}, 
         {"0.6", "0.007042"}, 
         {"0.65", "0.008"}, 
         {"0.7", "0.009804"}, 
         {"0.75", "0.0125"}, 
         {"0.8", "0.015625"}});
    model.component("comp1").func("int1").setIndex("fununit", "S/m", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "D_O2");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"0.2", "152e-10"}, 
         {"0.3", "115e-10"}, 
         {"0.4", "83e-10"}, 
         {"0.5", "49e-10"}, 
         {"0.55", "39e-10"}, 
         {"0.6", "28e-10"}, 
         {"0.65", "20e-10"}, 
         {"0.7", "15e-10"}, 
         {"0.75", "10e-10"}, 
         {"0.8", "8.5e-10"}});
    model.component("comp1").func("int2").setIndex("fununit", "m^2/s", 0);

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
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Concrete");
    model.component("comp1").material("mat2").set("family", "concrete");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").physics("tcd").selection().set(1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c", new String[]{"D_O2(PS)", "0", "0", "0", "D_O2(PS)", "0", "0", "0", "D_O2(PS)"});
    model.component("comp1").physics("tcd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("sigmal", new String[]{"sigma(PS)", "0", "0", "0", "sigma(PS)", "0", "0", "0", "sigma(PS)"});
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "C_O2_ref", 0);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(5, 6, 7, 8);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "oxide", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_oxide", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "M_oxide", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "Fe", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_Fe", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "M_Fe", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").label("\u6c27\u8fd8\u539f");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 4);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0", "c/C_O2_ref*i0_O2");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Ac", "A_O2");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").label("\u94c1\u6c27\u5316");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("nm", 4);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", 0.5, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vib", -1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vib", 2, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq", "Eeq_Fe");
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("i0", "i0_Fe");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Aa", "A_Fe");
    model.component("comp1").physics("tcd").feature("es1").create("er3", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er3").label("\u6790\u6c22");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Eeq", "Eeq_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er3")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("i0", "i0_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Ac", "A_H2");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "C_O2_ref", 0);
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new String[]{"e_init", "0", "0", "0", "e_init", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 2);
    model.component("comp1").physics("solid").feature("lemm2").selection().set(1);
    model.component("comp1").physics("solid").feature("lemm2").create("dmg1", "Damage", 2);
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("sigmap_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("sigmap", "sigmap");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("Gf_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("Gf", "Gf");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("regType", "impGradient");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("lint", "lint");
    model.component("comp1").physics("solid").feature("lemm2").feature("dmg1").set("hdmg", "hdmg");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,25,1500)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 10);
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 12);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 61, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("inherittubescale", false);
    model.result("pg2").feature("line1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 61, 0);
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 61, 0);
    model.result("pg4").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (tcd)");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result("pg4").feature("line1").set("unit", "\u00b5m");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 61, 0);
    model.result("pg5").label("\u6d53\u5ea6 (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tcd.tflux_cx", "tcd.tflux_cy"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 61, 0);
    model.result("pg6").label("\u5e94\u529b (solid)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u8150\u8680\u4ea7\u7269\u5c42\u539a\u5ea6");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{13, 25, 37, 49, 61}, 0);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(5, 6, 7, 8);
    model.result("pg7").feature("lngr1").set("expr", "tcd.sb_oxide");
    model.result("pg7").feature("lngr1").set("descr", "\u7535\u6781\u539a\u5ea6\u53d8\u5316\uff0c1 \u5206\u91cf");
    model.result("pg7").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").label("\u7b2c\u4e00\u4e3b\u5e94\u529b\u5206\u6790");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "solid.gpeval(solid.sdp1)");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def").active(false);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 61, 0);
    model.result("pg8").label("\u635f\u4f24 (solid)");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"solid.dmgGp"});
    model.result("pg8").feature("surf1").set("inheritplot", "none");
    model.result("pg8").feature("surf1").set("resolution", "normal");
    model.result("pg8").feature("surf1").set("colortabletype", "discrete");
    model.result("pg8").feature("surf1").set("bandcount", 11);
    model.result("pg8").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg8").feature("surf1").set("smooth", "none");
    model.result("pg8").feature("surf1").set("descractive", true);
    model.result("pg8").feature("surf1").set("descr", "\u635f\u4f24");
    model.result("pg8").label("\u635f\u4f24 (solid)");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("colortabletype", "continuous");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u88c2\u7eb9");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "solid.eeqnl");
    model.result("pg9").feature("surf1").set("descr", "\u975e\u5c40\u90e8\u7b49\u6548\u5e94\u53d8");
    model.result("pg9").run();

    model.title("\u94a2\u7b4b\u6df7\u51dd\u571f\u7684\u6c27\u5316\u9876\u5347");

    model
         .description("\u672c\u4f8b\u5bf9\u94a2\u7b4b\u6df7\u51dd\u571f\u7684\u6c27\u5316\u9876\u5347\u8fdb\u884c\u5efa\u6a21\u3002\u8150\u8680\u8fc7\u7a0b\u4f1a\u5728\u94a2\u7b4b\u4e0a\u5f62\u6210\u6c27\u5316\u5c42\uff0c\u8fdb\u800c\u5bfc\u81f4\u6df7\u51dd\u571f\u5185\u90e8\u4ea7\u751f\u5e94\u529b\u3002\u672c\u4f8b\u5728\u6df7\u51dd\u571f\u57df\u4e2d\u6a21\u62df\u7535\u8377\u548c\u6c27\u7684\u4f20\u8f93\uff0c\u5176\u4e2d\u7535\u89e3\u8d28\u7535\u5bfc\u7387\u548c\u6c27\u7684\u6269\u6563\u7387\u53d6\u51b3\u4e8e\u6c34\u5206\u542b\u91cf\u3002\u94a2\u7b4b\u548c\u6df7\u51dd\u571f\u5468\u56f4\u73af\u5883\u4f5c\u4e3a\u7ebf\u5f39\u6027\u6750\u6599\u8fdb\u884c\u5efa\u6a21\uff0c\u6bcf\u4e2a\u65f6\u6b65\u7684\u521d\u59cb\u5e94\u53d8\u90fd\u57fa\u4e8e\u6c27\u5316\u5c42\u7684\u539a\u5ea6\u8fdb\u884c\u8bbe\u7f6e\u3002750\u00a0\u5929\u540e\uff0c\u53ef\u4ee5\u89c2\u5bdf\u5230\u4ece\u94a2\u7b4b\u5230\u6df7\u51dd\u571f\u5916\u8868\u9762\u7684\u88c2\u7eb9\uff0c\u8fd9\u4f1a\u5f71\u54cd\u6df7\u51dd\u571f\u7ed3\u6784\u7684\u5b8c\u6574\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("oxide_jacking.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
