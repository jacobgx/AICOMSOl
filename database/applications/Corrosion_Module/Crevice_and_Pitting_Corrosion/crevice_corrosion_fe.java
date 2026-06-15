/*
 * crevice_corrosion_fe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:42 by COMSOL 6.3.0.290. */
public class crevice_corrosion_fe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Crevice_and_Pitting_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "WaterBased");
    model.component("comp1").physics("tcd").field("concentration").field("cFe");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cFe", "cFeOH", "cNa", "cCH3COOH", "cCH3COO", "cCH3COOFe"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("mu_factor", "4", "\u9ecf\u5ea6\u56e0\u5b50");
    model.param().set("DFe", "0.7e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cFe2+");
    model.param().set("DFeOH", "1e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cFeOH+");
    model.param().set("DNa", "1.3e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cNa+");
    model.param().set("DCH3COOH", "1.1e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cCH3COOH");
    model.param().set("DCH3COO", "1.1e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cCH3COO-");
    model.param().set("DCH3COOFe", "1.1e-7[dm^2/s]/mu_factor", "\u6269\u6563\u7cfb\u6570\uff0cCH3COOFe+");
    model.param().set("K1", "1.63e-7", "\u5e73\u8861\u5e38\u6570\uff0c\u94c1\u6eb6\u89e3\u5728\u6c34\u4e2d");
    model.param().set("Kw", "1.0113e-14[mol^2/dm^6]", "\u5e73\u8861\u5e38\u6570\uff0c\u6c34\u8d28\u5b50\u5316");
    model.param().set("K2", "1.75e-5", "\u5e73\u8861\u5e38\u6570\uff0c\u4e59\u9178\u8d28\u5b50\u5316");
    model.param().set("K3", "25.1", "\u5e73\u8861\u5e38\u6570\uff0c\u94c1\u6eb6\u89e3\u5728\u4e59\u9178\u4e2d");
    model.param().set("cCH3COO_mix", "0.5[mol/dm^3]", "CH3COO- \u6df7\u5408\u7269\u7684\u91cf");
    model.param().set("cCH3COOH_mix", "0.5[mol/dm^3]", "CH3COOH \u6df7\u5408\u7269\u7684\u91cf");
    model.param().set("L", "10[mm]", "\u7f1d\u9699\u957f\u5ea6");
    model.param().set("w", "0.5[mm]", "\u7f1d\u9699\u5bbd\u5ea6");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("pH0", "4.8", "\u521d\u59cb pH \u503c");
    model.param().set("cH0", "10^(-pH0)[mol/dm^3]", "\u521d\u59cb\u6d53\u5ea6\uff0cH+");
    model.param().set("cOH0", "(Kw/cH0)", "\u521d\u59cb\u6d53\u5ea6\uff0cOH-");
    model.param()
         .set("delta_c0", "(K2*cCH3COOH_mix-cH0*cCH3COO_mix/1[mol/dm^3])/(cH0/1[mol/dm^3]+K2)", "\u5e73\u8861\u53cd\u5e94\u5f15\u8d77\u7684\u6df7\u5408\u7269\u6d53\u5ea6\u53d8\u5316");
    model.param().set("cCH3COOH0", "cCH3COOH_mix-delta_c0", "\u521d\u59cb\u6d53\u5ea6\uff0cCH3COOH");
    model.param().set("cCH3COO0", "cCH3COO_mix+delta_c0", "\u521d\u59cb\u6d53\u5ea6\uff0cCH3COO-");
    model.param().set("cFe0", "1[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6\uff0cFe2+");
    model.param().set("cFeOH0", "K1*cFe0/cH0*1[mol/dm^3]", "\u521d\u59cb\u6d53\u5ea6\uff0cFeOH+");
    model.param().set("cCH3COOFe0", "K3*cCH3COO0*cFe0/1[mol/dm^3]", "\u521d\u59cb\u6d53\u5ea6\uff0cCH3COOFe+");
    model.param()
         .set("cNa0", "cOH0+cCH3COO0-(2*cFe0+cFeOH0+cH0+cCH3COOFe0)", "\u521d\u59cb\u6d53\u5ea6\uff0cNa+\uff08\u6765\u81ea\u7535\u4e2d\u6027\uff09");
    model.param()
         .set("phil_mouth", "0.16[V]", "\u7f1d\u9699\u53e3\u6d4b\u5f97\u7684\u7535\u89e3\u8d28\u7535\u4f4d vs. SHE");
    model.param().set("V_pol", "0.84[V]", "\u6781\u5316\u7535\u4f4d vs. SHE");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Fe in acetic acid/sodium acetate (Anodic)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-1", "0.1"}, 
         {"-0.452247191", "0.1"}, 
         {"-0.41011236", "1"}, 
         {"-0.33988764", "10"}, 
         {"-0.283707865", "30"}, 
         {"-0.199438202", "100"}, 
         {"0.011235955", "350"}, 
         {"0.193820225", "550"}, 
         {"0.306179775", "600"}, 
         {"0.404494382", "550"}, 
         {"0.502808989", "350"}, 
         {"0.629213483", "100"}, 
         {"0.699438202", "10"}, 
         {"0.769662921", "2"}, 
         {"0.867977528", "1"}, 
         {"1.387640449", "1"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"A/m^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "J. C. Walton, \"Mathematical modeling of mass transport and chemical reaction in crevice and pitting corrosion\", Corrosion Science, vol. 30, p. 915, 1990.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_ref_exp_vs_SHE", "0 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c SHE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "J. C. Walton, \"Mathematical modeling of mass transport and chemical reaction in crevice and pitting corrosion\", Corrosion Science, vol. 30, p. 915, 1990.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-1 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("E_ref_exp_vs_SHE", "0 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 4);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 5);
    model.component("comp1").physics("tcd").create("hcpce1", "HighlyConductivePorousElectrode", 1);
    model.component("comp1").physics("tcd").feature("hcpce1").selection().all();
    model.component("comp1").physics("tcd").feature("hcpce1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tcd").feature("hcpce1").set("minput_temperature", "T");
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cFe", new String[]{"DFe", "0", "0", "0", "DFe", "0", "0", "0", "DFe"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cFeOH", new String[]{"DFeOH", "0", "0", "0", "DFeOH", "0", "0", "0", "DFeOH"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cNa", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCH3COOH", new String[]{"DCH3COOH", "0", "0", "0", "DCH3COOH", "0", "0", "0", "DCH3COOH"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCH3COO", new String[]{"DCH3COO", "0", "0", "0", "DCH3COO", "0", "0", "0", "DCH3COO"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCH3COOFe", new String[]{"DCH3COOFe", "0", "0", "0", "DCH3COOFe", "0", "0", "0", "DCH3COOFe"});
    model.component("comp1").physics("tcd").feature("hcpce1").set("epsl", 1);
    model.component("comp1").physics("tcd").feature("hcpce1").set("DiffusionCorrModel", "NoCorr");
    model.component("comp1").physics("tcd").feature("hcpce1").set("phisext0", "V_pol");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("Av", "2/w");
    model.component("comp1").physics("tcd").create("eqreac1", "EquilibriumReaction", 1);
    model.component("comp1").physics("tcd").feature("eqreac1").selection().all();
    model.component("comp1").physics("tcd").feature("eqreac1").set("Keq0", "K1");
    model.component("comp1").physics("tcd").feature("eqreac1").setIndex("nu", -1, 0);
    model.component("comp1").physics("tcd").feature("eqreac1").setIndex("nu", 1, 1);
    model.component("comp1").physics("tcd").feature("eqreac1").set("nuH", 1);
    model.component("comp1").physics("tcd").feature().duplicate("eqreac2", "eqreac1");
    model.component("comp1").physics("tcd").feature("eqreac2").set("Keq0", "K2");
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", 0, 0);
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", 0, 1);
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", -1, 3);
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", 1, 4);
    model.component("comp1").physics("tcd").feature().duplicate("eqreac3", "eqreac2");
    model.component("comp1").physics("tcd").feature("eqreac3").set("Keq0", "K3");
    model.component("comp1").physics("tcd").feature("eqreac3").setIndex("nu", -1, 0);
    model.component("comp1").physics("tcd").feature("eqreac3").setIndex("nu", 0, 3);
    model.component("comp1").physics("tcd").feature("eqreac3").setIndex("nu", -1, 4);
    model.component("comp1").physics("tcd").feature("eqreac3").setIndex("nu", 1, 5);
    model.component("comp1").physics("tcd").feature("eqreac3").set("nuH", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cFe0", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cFeOH0", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cNa0", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCH3COOH0", 3);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCH3COO0", 4);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCH3COOFe0", 5);
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "phil_mouth");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cFe0", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cNa0", 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cCH3COOH0", 3);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(1);
    model.component("comp1").physics("tcd").feature("eip1").set("philbnd", "phil_mouth");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1e-5");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-7");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "mu_factor", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "mu_factor", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_pol", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(-0.6, 0.2, 0.8) 0.844", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "0.844 V(SHE) \u65f6\u7684\u6eb6\u6db2\u6210\u5206");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u7f1d\u9699\u5185\u4f4d\u7f6e (m)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "cFe");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "Fe<sup>2+</sup>", 0);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "tcd.cH");
    model.result("pg1").feature("lngr2").setIndex("legends", "H<sup>+</sup>", 0);
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").set("expr", "tcd.cOH");
    model.result("pg1").feature("lngr3").setIndex("legends", "OH<sup>-</sup>", 0);
    model.result("pg1").feature().duplicate("lngr4", "lngr3");
    model.result("pg1").run();
    model.result("pg1").feature("lngr4").set("expr", "cFeOH");
    model.result("pg1").feature("lngr4").setIndex("legends", "FeOH<sup>+</sup>", 0);
    model.result("pg1").feature().duplicate("lngr5", "lngr4");
    model.result("pg1").run();
    model.result("pg1").feature("lngr5").set("expr", "cNa");
    model.result("pg1").feature("lngr5").setIndex("legends", "Na<sup>+</sup>", 0);
    model.result("pg1").feature().duplicate("lngr6", "lngr5");
    model.result("pg1").run();
    model.result("pg1").feature("lngr6").set("expr", "cCH3COOH");
    model.result("pg1").feature("lngr6").setIndex("legends", "CH3COOH", 0);
    model.result("pg1").feature().duplicate("lngr7", "lngr6");
    model.result("pg1").run();
    model.result("pg1").feature("lngr7").set("expr", "cCH3COO");
    model.result("pg1").feature("lngr7").setIndex("legends", "CH3COO<sup>-</sup>", 0);
    model.result("pg1").feature().duplicate("lngr8", "lngr7");
    model.result("pg1").run();
    model.result("pg1").feature("lngr8").set("expr", "cCH3COOFe");
    model.result("pg1").feature("lngr8").setIndex("legends", "CH3COOFe<sup>+</sup>", 0);
    model.result("pg1").feature("lngr8").set("linestyle", "dashed");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", "0.99e-6");
    model.result("pg1").set("xmax", 0.0101);
    model.result("pg1").set("ymin", "1e-3");
    model.result("pg1").set("ymax", "1e4");
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6781\u7535\u4f4d");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u7f1d\u9699\u5185\u4f4d\u7f6e (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u4f4d (V)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u7535\u89e3\u8d28\u4e2d\u7535\u6781\u7535\u4f4d\u4e0e\u53c2\u6bd4\u7535\u6781\u7684\u5173\u7cfb");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "V_pol-phil");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8150\u8680\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u94c1\u6c27\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "tcd.iloc_per1");
    model.result("pg3").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg3").feature("lngr1").set("xdatadescractive", true);
    model.result("pg3").feature("lngr1").set("xdatadescr", "\u7f1d\u9699\u5185\u4f4d\u7f6e");
    model.result("pg3").run();

    model.title("\u4e59\u9178/\u4e59\u9178\u94a0\u6eb6\u6db2\u4e2d\u7684\u94c1\u7f1d\u9699\u8150\u8680");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u94c1\u5728 pH \u503c\u4e3a 4.8 \u7684\u7f13\u51b2\u6eb6\u6db2\u4e2d\u7684\u7f1d\u9699\u8150\u8680\uff0c\u8be5\u6eb6\u6db2\u7531\u7b49\u91cf\u7684\u4e59\u9178\u548c\u4e59\u9178\u94a0\u7ec4\u6210\u3002\u6b64\u6a21\u578b\u8026\u5408\u4e86\u7f1d\u9699\u58c1\u4e0a\u94c1\u7684\u7535\u5316\u5b66\u6eb6\u89e3\u548c\u7535\u89e3\u8d28\u4e2d\u7684\u5f02\u8d28\u5e73\u8861\u53cd\u5e94\u3002\u8fd9\u662f\u4e00\u4e2a\u4e00\u7ef4\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("crevice_corrosion_fe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
