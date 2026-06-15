/*
 * molten_carbonate_transport.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:08 by COMSOL 6.3.0.290. */
public class molten_carbonate_transport {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cet", "ConcentratedElectrolyteTransport", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/cet", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cet", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Av_neg", "2.7e5[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef");
    model.param().set("i0_neg_ref", "225[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_neg_ref", "0[V]", "\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param().set("L_neg", "780[um]", "\u7535\u6781\u539a\u5ea6");
    model.param().set("eps_neg", "0.52", "\u5b54\u9699\u7387");
    model.param().set("eps_pos", "0.62", "\u5b54\u9699\u7387");
    model.param().set("epsl_neg", "0.4*eps_neg", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_pos", "0.4*eps_pos", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("L_mem", "1[mm]", "\u819c\u539a");
    model.param().set("Av_pos", "3e4[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef");
    model.param().set("i0_pos_ref", "10.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_pos_ref", "1.02[V]", "\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param().set("L_pos", "800[um]", "\u7535\u6781\u539a\u5ea6");
    model.param().set("eps_mem", "0.55", "\u5b54\u9699\u7387");
    model.param().set("epsl_mem", "1*0.55", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("P_Li2CO3", "0.62", "\u6469\u5c14\u6bd4\uff0cLi2CO3");
    model.param().set("P_K2CO3", "0.38", "\u6469\u5c14\u6bd4\uff0cK2CO3");
    model.param().set("T", "650[degC]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("xH2_neg", "0.56", "H2 \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xCO_neg", "0.08", "CO \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xCO2_neg", "0.08", "CO2 \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xH2O_neg", "1-xH2_neg-xCO_neg-xCO2_neg", "H2O \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xO2_pos", "0.143", "O2 \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xCO2_pos", "0.285", "O2 \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xN2_pos", "0.522", "N2 \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("xH2O_pos", "1-xO2_pos-xCO2_pos-xN2_pos", "H2O \u6c14\u4f53\u6469\u5c14\u5206\u6570");
    model.param().set("beta_neg", "0.5", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("beta_pos", "0.5", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("M_Li", "6.9[g/mol]", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_K", "39.1[g/mol]", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_CO3", "60[g/mol]", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("E_cell", "0.75[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("rho", "1.925e3[kg/m^3]", "\u7535\u89e3\u8d28\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_mem", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cet").prop("Species").setIndex("CationNames", "Li", 0, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationMasses", "M_Li", 0, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationNames", "Cat", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationMasses", "1[g/mol]", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationCharges", 1, 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationMasses", "1[g/mol]", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationCharges", 1, 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationNames", "Cat", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationMasses", "1[g/mol]", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationCharges", 1, 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationNames", "K", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("CationMasses", "M_K", 1, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("AnionNames", "CO3", 0, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("AnionMasses", "M_CO3", 0, 0);
    model.component("comp1").physics("cet").prop("Species").setIndex("AnionCharges", 2, 0, 0);
    model.component("comp1").physics("cet").prop("Species").set("NeutralSpeciesNames", new int[][]{});
    model.component("comp1").physics("cet").prop("Species").set("NeutralMasses", new int[][]{});
    model.component("comp1").physics("cet").feature("ref1").set("s_Li", 0);
    model.component("comp1").physics("cet").feature("ref1").set("s_CO3", 1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").physics("cet").feature("el1").set("ConcentrationDefinition", "DensityAndMolarMasses");
    model.component("comp1").physics("cet").feature("el1").set("rho", "rho");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("xA", "cet.x_Li/(cet.x_Li+cet.x_K)", "Li2CO3 \u76d0\u6469\u5c14\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("D_Li_CO3", "(5.73e-7-3.77e-7*xA)*exp(-4.5e4[J/mol]/(R_const*T))[m^2/s]", "Maxwell-Stefan \u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("D_K_CO3", "(1.25e-8+4.86e-7*xA)*exp(-4.5e4[J/mol]/(R_const*T))[m^2/s]", "Maxwell-Stefan \u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("D_Li_K", "(3.13e-7-1e-7*xA)*exp(-4.49e4[J/mol]/(R_const*T))[m^2/s]", "Maxwell-Stefan \u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("i0_neg", "i0_neg_ref*xH2_neg^(0.5*beta_neg)*xH2O_neg^(0.5*(1-beta_neg))*xCO2_neg^(0.5*(1-beta_neg))", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("phis_neg", "0", "\u7535\u6781\u76f8\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("Eeq_neg", "Eeq_neg_ref-R_const*T/(2*F_const)*log(xH2_neg/(xH2O_neg*xCO2_neg))", "\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var1").set("eta_neg", "phis_neg-cet.phil-Eeq_neg", "\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("iloc_neg", "i0_neg*(exp((1-beta_neg)*F_const/(R_const*T)*eta_neg)-exp(-(1+beta_neg)*F_const/(R_const*T)*eta_neg))", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("iv_neg", "iloc_neg*Av_neg", "\u4f53\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("i0_pos", "i0_pos_ref*xO2_pos^(0.5-0.25*beta_pos)*xCO2_pos^(-(1+0.5*beta_pos))", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("phis_pos", "E_cell", "\u7535\u6781\u76f8\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("Eeq_pos", "Eeq_pos_ref-R_const*T/(2*F_const)*log(1/(xCO2_pos*xO2_pos^0.5))", "\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var1").set("eta_pos", "phis_pos-cet.phil-Eeq_pos", "\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("iloc_pos", "i0_pos*(exp((1+beta_pos)*F_const/(R_const*T)*eta_pos)-exp(-(1-beta_pos)*F_const/(R_const*T)*eta_pos))", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("iv_pos", "iloc_pos*Av_pos", "\u4f53\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").physics("cet").feature("el1").feature("dc1").set("D_LiK", "D_Li_K");
    model.component("comp1").physics("cet").feature("el1").feature("dc1").set("D_LiCO3", "D_Li_CO3");
    model.component("comp1").physics("cet").feature("el1").feature("dc1").set("D_KCO3", "D_K_CO3");
    model.component("comp1").physics("cet").feature("init1")
         .set("ElectrolyteComponentInitialValuesType", "InitialMolarProportions");
    model.component("comp1").physics("cet").feature("init1").set("P0_Li_2_CO3", "P_Li2CO3");
    model.component("comp1").physics("cet").feature("init1").set("P0_K_2_CO3", "P_K2CO3");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(1);
    model.component("comp1").variable("var2").set("R_CO3", "-iv_neg/(2*F_const)");
    model.component("comp1").variable("var2").descr("R_CO3", "Carbonate molar source rate");
    model.component("comp1").variable("var2").set("epsl", "epsl_neg");
    model.component("comp1").variable("var2").descr("epsl", "Electrolyte volume fraction");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").selection().set(2);
    model.component("comp1").variable("var3").set("R_CO3", "0");
    model.component("comp1").variable("var3").set("epsl", "epsl_mem");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").selection().set(3);
    model.component("comp1").variable("var4").set("R_CO3", "-iv_pos/(2*F_const)");
    model.component("comp1").variable("var4").set("epsl", "epsl_pos");

    model.component("comp1").physics("cet").create("sep1", "Separator", 1);
    model.component("comp1").physics("cet").feature("sep1").selection().all();
    model.component("comp1").physics("cet").feature("sep1").set("epsl", "epsl");
    model.component("comp1").physics("cet").create("ss1", "SpeciesSources", 1);
    model.component("comp1").physics("cet").feature("ss1").selection().all();
    model.component("comp1").physics("cet").feature("ss1").set("R_CO3", "R_CO3");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cet)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"cet.phil"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (cet)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"cet.c_Li"});
    model.result("pg2").feature("lngr1").label("\u7269\u8d28 Li");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoexpr", false);
    model.result("pg2").feature("lngr1").set("autodescr", false);
    model.result("pg2").feature("lngr1").set("legendprefix", "Li ");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2, 3);
    model.result("pg2").feature("lngr2").set("expr", new String[]{"cet.c_K"});
    model.result("pg2").feature("lngr2").label("\u7269\u8d28 K");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoexpr", false);
    model.result("pg2").feature("lngr2").set("autodescr", false);
    model.result("pg2").feature("lngr2").set("legendprefix", "K ");
    model.result("pg2").feature("lngr2").set("descractive", true);
    model.result("pg2").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "x");
    model.result("pg2").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr3").selection().set(1, 2, 3);
    model.result("pg2").feature("lngr3").set("expr", new String[]{"cet.c_CO3"});
    model.result("pg2").feature("lngr3").label("\u7269\u8d28 CO3");
    model.result("pg2").feature("lngr3").set("legend", true);
    model.result("pg2").feature("lngr3").set("autosolution", false);
    model.result("pg2").feature("lngr3").set("autoexpr", false);
    model.result("pg2").feature("lngr3").set("autodescr", false);
    model.result("pg2").feature("lngr3").set("legendprefix", "CO3 ");
    model.result("pg2").feature("lngr3").set("descractive", true);
    model.result("pg2").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, Li (cet)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"cet.c_Li"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, K (cet)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cet.c_K"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, CO3 (cet)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cet.c_CO3"});
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("ylabelactive", true);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", "cet.ss1.iv");
    model.result("pg6").feature("lngr1").set("descr", "\u4f53\u79ef\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("lngr1").set("xdata", "spacevar");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "cet.Ilx");
    model.result("pg7").feature("lngr1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "cet.sigmal");
    model.result("pg8").feature("lngr1").set("descr", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "xA");
    model.result("pg9").feature("lngr1").set("descr", "Li2CO3 \u76d0\u6469\u5c14\u5206\u6570");
    model.result("pg9").run();

    model.title("\u7194\u878d\u78b3\u9178\u76d0\u4f20\u8f93");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7531\u4e24\u79cd\u4e8c\u5143\u76d0\u7ec4\u6210\u7684\u76d0\u7194\u4f53\u4e2d\u5355\u4e2a\u79bb\u5b50\u7684\u4f20\u8f93\u8fc7\u7a0b\uff0c\u5176\u4e2d\u4f20\u8f93\u65b9\u7a0b\u57fa\u4e8e\u6d53\u6eb6\u6db2\u7406\u8bba\u8fdb\u884c\u5b9a\u4e49\u3002\u793a\u4f8b\u6a21\u578b\u4e2d\u5b9a\u4e49\u4e86\u7194\u878d\u78b3\u9178\u76d0\u71c3\u6599\u7535\u6c60\uff08\u6216\u7535\u89e3\u69fd\uff09\uff0c\u91c7\u7528\u7531\u4e00\u4e2a\u9694\u819c\u3001\u4e00\u4e2a\u8d1f\u6781\uff08\u6c22\uff09\u548c\u4e00\u4e2a\u6b63\u6781\uff08\u6c27\uff09\u591a\u5b54\u7535\u6781\u7ec4\u6210\u7684\u4e00\u7ef4\u6a21\u578b\u51e0\u4f55\u3002\n\n\u6a21\u578b\u901a\u8fc7\u77ac\u6001\u6c42\u89e3\u5668\u8fdb\u884c\u6c42\u89e3\uff0c\u4ee5\u6a21\u62df\u5728 1 h \u6052\u7535\u4f4d\u4fdd\u6301\u8fc7\u7a0b\u4e2d\u79bb\u5b50\u5206\u5e03\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("molten_carbonate_transport.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
