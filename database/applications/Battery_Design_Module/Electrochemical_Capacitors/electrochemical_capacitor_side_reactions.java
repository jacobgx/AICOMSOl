/*
 * electrochemical_capacitor_side_reactions.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class electrochemical_capacitor_side_reactions {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Electrochemical_Capacitors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "WaterBased");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_sep", "25[um]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("L_elec", "50[um]", "\u7535\u6781\u957f\u5ea6");
    model.param().set("kappa_l", "0.08[S/cm]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("sigma_s", "10[S/cm]", "\u7535\u5bfc\u7387");
    model.param().set("eps_el", "0.7", "\u7535\u6781\u7684\u5b54\u9699\u7387");
    model.param().set("eps_sep", "0.7", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("c_bulk", "1[M]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("T", "25[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("aC", "20[uF/cm^2]", "\u9762\u79ef\u6bd4\u7535\u5bb9");
    model.param().set("D", "kappa_l*(R_const*T)/F_const^2/c_bulk/2", "\u6269\u6563\u7cfb\u6570");
    model.param().set("Av", "4.5e6[cm^2/cm^3]", "\u6bd4\u9762\u79ef");
    model.param().set("Cdl", "aC/Av", "\u9762\u79ef\u6bd4\u7535\u5bb9");
    model.param().label("\u53c2\u6570\uff1a\u7535\u5316\u5b66\u7535\u6c60");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u8d1f\u8f7d\u66f2\u7ebf");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("I_app", "100[A]", "\u5916\u52a0\u7535\u6d41");
    model.param("par2").set("V_max", "2[V]", "\u6700\u5927\u7535\u538b");
    model.param("par2").set("V_min", "0.5[V]", "\u6700\u5c0f\u7535\u538b");
    model.param("par2").set("t_rest", "3600[s]", "\u9759\u7f6e\u65f6\u95f4");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u7535\u6781\u53cd\u5e94");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("Eeq_ref_O2", "1.23[V]", "H2 \u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param("par3").set("Eeq_ref_H2", "0[V]", "O2 \u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param("par3").set("cH2_init", "1e-10[mol/m^3]", "H2 \u7684\u521d\u59cb\u538b\u529b");
    model.param("par3").set("cO2_init", "1e-10[mol/m^3]", "O2 \u7684\u521d\u59cb\u538b\u529b");
    model.param("par3")
         .set("i0_ref_H2", "6.29e-5[mA/cm^2]", "H2 \u5728\u53c2\u8003\u6761\u4ef6\u4e0b\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param("par3")
         .set("i0_ref_O2", "1.24e-7[mA/cm^2]", "O2 \u5728\u53c2\u8003\u6761\u4ef6\u4e0b\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param("par3")
         .set("cO2_sol", "40[mg/L]/18[g/mol]", "\u6c27\u5728 1 \u4e2a\u5927\u6c14\u538b\u4e0b\u7684\u6eb6\u89e3\u5ea6");
    model.param("par3")
         .set("cH2_sol", "1.6[mg/L]/2[g/mol]", "\u6c22\u5728 1 \u4e2a\u5927\u6c14\u538b\u4e0b\u7684\u6eb6\u89e3\u5ea6");
    model.param("par3").set("D_O2", "1e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d\u6c27\u7684\u6269\u6563\u7cfb\u6570");
    model.param("par3").set("D_H2", "2e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d\u6c22\u7684\u6269\u6563\u7cfb\u6570");
    model.param("par3")
         .set("E_cell_init", "Eeq_ref_O2-Eeq_ref_H2+R_const*T/(4*F_const)*log(cO2_init/cO2_sol*(cH2_init/cH2_sol)^2)", "\u521d\u59cb\u7535\u6c60\u7535\u4f4d");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_elec", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_elec", 2);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c1", "c2", "c3", "c4"});
    model.component("comp1").physics("tcd").field("concentration").component(1, "cCat");
    model.component("comp1").physics("tcd").field("concentration").component(2, "cAn");
    model.component("comp1").physics("tcd").field("concentration").component(3, "cO2");
    model.component("comp1").physics("tcd").field("concentration").component(4, "cH2");
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCat", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cAn", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cO2", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cH2", new String[]{"D_H2", "0", "0", "0", "D_H2", "0", "0", "0", "D_H2"});
    model.component("comp1").physics("tcd").feature("init1").label("\u521d\u59cb\u503c - H2 \u4fa7");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cH2_init", 3);
    model.component("comp1").physics("tcd").create("init2", "init", 1);
    model.component("comp1").physics("tcd").feature("init2").selection().set(3);
    model.component("comp1").physics("tcd").feature("init2").label("\u521d\u59cb\u503c - O2 \u4fa7");
    model.component("comp1").physics("tcd").feature("init2").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("init2").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").feature("init2").setIndex("initc", "cO2_init", 2);
    model.component("comp1").physics("tcd").feature("init2").set("initphis", "E_cell_init");
    model.component("comp1").physics("tcd").create("init3", "init", 1);
    model.component("comp1").physics("tcd").feature("init3").label("\u521d\u59cb\u503c - \u9694\u819c");
    model.component("comp1").physics("tcd").feature("init3").selection().set(2);
    model.component("comp1").physics("tcd").feature("init3").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("init3").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").feature("init3").setIndex("initc", "cO2_init*(x-L_elec)/L_sep", 2);
    model.component("comp1").physics("tcd").feature("init3").setIndex("initc", "cH2_init*(L_elec+L_sep-x)/L_sep", 3);
    model.component("comp1").physics("tcd").feature("init3").set("initphis", "E_cell_init");
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("tcd").feature("pce1").label("\u591a\u5b54\u7535\u6781 - H2 \u4fa7");
    model.component("comp1").physics("tcd").feature("pce1").selection().set(1);
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cCat", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cAn", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cO2", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cH2", new String[]{"D_H2", "0", "0", "0", "D_H2", "0", "0", "0", "D_H2"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("tcd").feature("pce1").set("epss", "1-eps_el");
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "eps_el");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94 - HER");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Eeq_ref", "Eeq_ref_H2");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("i0_ref", "i0_ref_H2");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Av", "Av");
    model.component("comp1").physics("tcd").feature("pce1").create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").set("Cdl", "aC");
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").set("av_dl", "Av");
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").setIndex("Vi0", -0.5, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").setIndex("Vi0", 0.5, 1);
    model.component("comp1").physics("tcd").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("tcd").feature("pce2").label("\u591a\u5b54\u7535\u6781 - O2 \u4fa7");
    model.component("comp1").physics("tcd").feature("pce2").selection().set(3);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94 - OER");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("nm", 4);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").setIndex("Vi0", 0, 3);
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("Eeq_ref", "Eeq_ref_O2");
    model.component("comp1").physics("tcd").feature("pce2").feature("per1").set("i0_ref", "i0_ref_O2");
    model.component("comp1").physics("tcd").create("bei1", "InternalElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("bei1").label("\u5185\u90e8\u7535\u6781\u8868\u9762 - ORR");
    model.component("comp1").physics("tcd").feature("bei1").selection().set(2);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").set("nm", 4);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("bei1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("bei1").feature("er1")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("tcd").feature("bei1").feature("er1")
         .set("RateLimitingSpeciesConcentration", 3);
    model.component("comp1").physics("tcd").feature().duplicate("bei2", "bei1");
    model.component("comp1").physics("tcd").feature("bei2").selection().set(3);
    model.component("comp1").physics("tcd").feature("bei2").label("\u5185\u90e8\u7535\u6781\u8868\u9762 - HOR");
    model.component("comp1").physics("tcd").feature("bei2").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("bei2").feature("er1").setIndex("Vi0", 0, 2);
    model.component("comp1").physics("tcd").feature("bei2").feature("er1").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd").feature("bei2").feature("er1")
         .set("RateLimitingSpeciesConcentration", 4);
    model.component("comp1").physics("tcd").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("tcd").feature("egnd1").selection().set(1);
    model.component("comp1").physics("tcd").create("cdc1", "ChargeDischargeCycling", 0);
    model.component("comp1").physics("tcd").feature("cdc1").selection().set(4);
    model.component("comp1").physics("tcd").feature("cdc1").set("Idch", "-I_app");
    model.component("comp1").physics("tcd").feature("cdc1").set("Vmin", "V_min");
    model.component("comp1").physics("tcd").feature("cdc1").set("Ich", "I_app");
    model.component("comp1").physics("tcd").feature("cdc1").set("Vmax", "V_max");
    model.component("comp1").physics("tcd").feature("cdc1").set("item.OCCH", true);
    model.component("comp1").physics("tcd").feature("cdc1").set("trech", "t_rest");
    model.component("comp1").physics("tcd").feature("cdc1").set("StartWith", "Charge_first");
    model.component("comp1").physics("tcd").feature("cdc1").set("phis0init", "E_cell_init");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("probename", "aH2");
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").set("expr", "comp1.cH2/cH2_sol");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe("dom1").set("descr", "aH2");
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").set("probename", "aO2");
    model.component("comp1").probe("dom2").set("expr", "comp1.cO2/cO2_sol");
    model.component("comp1").probe("dom2").set("descr", "aO2");
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "tcd.cdc1.phis0");
    model.component("comp1").probe("var1").set("descr", "\u7535\u6c60\u7535\u4f4d");
    model.component("comp1").probe("var1").set("probename", "E_cell");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().all();

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6709\u9759\u606f\u671f\u7684 CC \u5145\u7535");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "0 5000");
    model.study("std1").showAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");
    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41\u7535\u538b\u66f2\u7ebf");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").label("\u7535\u6c60\u7535\u4f4d");
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"tcd.cdc1.Icell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u6d41"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg2").feature("glob1").label("\u7535\u6c60\u7535\u6d41");
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6700\u5927\u6d3b\u6027");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6d3b\u6027 (1)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").label("O2 \u7684\u6d3b\u6027");
    model.result("pg3").feature("glob1").setIndex("expr", "maxop1(cO2/cO2_sol)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "O2 \u7684\u6d3b\u6027", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").label("H2 \u7684\u6d3b\u6027");
    model.result("pg3").feature("glob2").setIndex("expr", "maxop1(cH2/cH2_sol)", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "H2 \u7684\u6d3b\u6027", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6c22\u6c14\u548c\u6c27\u6c14\u7684\u6d53\u5ea6 - \u5145\u7535\u7ed3\u675f\u65f6");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65e0\u91cf\u7eb2\u957f\u5ea6 (1)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").label("\u6c27\u6c14\u6d53\u5ea6");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "cO2");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "O2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x/(2*L_elec+L_sep)");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1").set("autodescr", true);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").label("\u6c22\u6c14\u6d53\u5ea6");
    model.result("pg4").feature("lngr2").set("expr", "cH2");
    model.result("pg4").feature("lngr2").set("descr", "H2");
    model.result("pg4").run();

    model.title("\u7535\u5316\u5b66\u7535\u5bb9\u5668\u4e2d\u7684\u5bc4\u751f\u53cd\u5e94");

    model
         .description("\u672c\u6a21\u578b\u9610\u8ff0\u6c27\u548c\u6c22\u7684\u5f62\u6210\u4e0e\u590d\u5408\u5bf9\u5177\u6709\u542b\u6c34\u7535\u89e3\u8d28\u7684\u7535\u5316\u5b66\u7535\u5bb9\u5668\u7684\u6027\u80fd\u548c\u81ea\u653e\u7535\u7684\u5f71\u54cd\u3002\n\n\u5176\u4e2d\u7814\u7a76\u4e00\u79cd\u7531\u6df7\u5408\u6052\u6d41\u8109\u51b2\u548c\u5f00\u8def\u4f11\u7720\u671f\u7ec4\u6210\u7684\u8d1f\u8f7d\u5468\u671f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electrochemical_capacitor_side_reactions.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
