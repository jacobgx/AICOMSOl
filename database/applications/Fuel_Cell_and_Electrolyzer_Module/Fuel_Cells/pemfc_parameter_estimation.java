/*
 * pemfc_parameter_estimation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class pemfc_parameter_estimation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E_cell", "0.7[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("sigmal_mem", "10[S/m]", "\u819c\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param()
         .set("log10_i0_ref_ORR", "-3", "ORR \u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\u7684\u5bf9\u6570\uff08\u62df\u5408\u53c2\u6570\uff09");
    model.param()
         .set("alphac_ORR", "log(10)*R_const*T/(100[mV]*F_const)", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param()
         .set("ilim_ORR_ref", "3000[A/m^2]", "1 \u4e2a\u5927\u6c14\u538b\u5206\u538b\u7684\u6c27\u8fd8\u539f\u6781\u9650\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("H_gdl", "150[um]", "GDL \u539a\u5ea6");
    model.param().set("H_ct", "8[um]", "\u50ac\u5316\u5c42\u539a\u5ea6");
    model.param().set("H_mem", "9[um]", "\u819c\u539a\u5ea6");
    model.param().set("W_ch", "1[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_rib", "1[mm]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("W_cell", "(W_ch+W_rib)/2", "\u8ba1\u7b97\u7684\u7535\u6c60\u5bbd\u5ea6");
    model.param().set("H_cell", "2*H_gdl+H_mem+2*H_ct", "\u8ba1\u7b97\u7684\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("T", "100[degC]", "\u7535\u6c60\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("RH", "70[%]", "\u8fdb\u6c14\u7684\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("xN2", "79[%]", "\u5e72\u71e5\u6c2e\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("sigmas_ct", "50[S/m]", "\u50ac\u5316\u5c42\u7684\u7535\u5bfc\u7387");
    model.param().set("sigmas_gdl_TP", "70[S/m]", "gdl \u7684\u7a7f\u9762\u7535\u5bfc\u7387");
    model.param().set("sigmas_gdl_IP", "5000[S/m]", "gdl \u7684\u9762\u5185\u7535\u5bfc\u7387");
    model.param()
         .set("epsg_ct", "0.3", "\u50ac\u5316\u5c42\u4e2d\u7684\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param().set("epsg_gdl", "0.7", "gdl \u4e2d\u7684\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_ct", "0.3", "\u50ac\u5316\u5c42\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("perm_ct", "1e-12[m^2]", "\u50ac\u5316\u5c42\u7684\u6c34\u529b\u6e17\u900f\u7387");
    model.param().set("perm_gdl", "1e-10[m^2]", "gdl \u7684\u6c34\u529b\u6e17\u900f\u7387");
    model.param().set("alphaa_ORR", "4-alphac_ORR", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param().set("E_init", "0.8[V]", "\u521d\u59cb\u7535\u6c60\u7535\u538b");
    model.param()
         .set("sigmal_ct", "0.5[S/m]", "\u50ac\u5316\u5c42\u4e2d\u7684\u6709\u6548\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param()
         .set("i0_ref_ORR", "10^log10_i0_ref_ORR[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param().set("Av_ORR", "1.7e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef\uff0c\u6c27\u8fd8\u539f");
    model.param()
         .set("i0_ref_HOR", "100[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param().set("Av_HOR", "7.1e6[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef\uff0c\u6c22\u6c27\u5316");
    model.param().set("perm_H2", "8.2E-14[s*mol/kg]", "\u6c22\u819c\u6e17\u900f\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_cell", "H_gdl"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W_cell", "H_ct"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "H_gdl"});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W_cell", "H_mem"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "H_gdl+H_ct"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"W_cell", "H_ct"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "H_gdl+H_ct+H_mem"});
    model.component("comp1").geom("geom1").feature("r4").set("selresult", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"W_cell", "H_gdl"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "H_gdl+H_ct+H_mem+H_ct"});
    model.component("comp1").geom("geom1").feature("r5").set("selresult", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "W_rib/2", 0);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "H_cell", 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("I_cell_avg", "comp1.intop_cc(fc.nIs)/W_cell", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("ilim_ORR", "ilim_ORR_ref*fc.pO2/1[atm]", "\u5c40\u90e8\u6781\u9650\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_cc");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);

    model.component("comp1").view("view1").set("showlabels", true);

    model.component("comp1").cpl("intop1").selection().set(11);

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2_mem", true);
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("h2gde1").selection().named("geom1_r2_dom");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("sigmas", new String[]{"sigmas_ct", "0", "0", "0", "sigmas_ct", "0", "0", "0", "sigmas_ct"});
    model.component("comp1").physics("fc").feature("h2gde1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("fc").feature("h2gde1").set("fl", 1);
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_HOR");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "Av_HOR");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("o2gde1").selection().named("geom1_r4_dom");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"sigmas_ct", "0", "0", "0", "sigmas_ct", "0", "0", "0", "sigmas_ct"});
    model.component("comp1").physics("fc").feature("o2gde1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("fc").feature("o2gde1").set("fl", 1);
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "epsg_ct");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("kappag", new String[]{"perm_ct", "0", "0", "0", "perm_ct", "0", "0", "0", "perm_ct"});
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_ref_ORR");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphaa", "alphaa_ORR");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("ilim", "ilim_ORR");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "Av_ORR");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 2);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_TP", "0", "0", "0", "sigmas_gdl_IP"});
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 2);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_r5_dom");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_TP", "0", "0", "0", "sigmas_gdl_IP"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_gdl");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("kappag", new String[]{"perm_gdl", "0", "0", "0", "perm_gdl", "0", "0", "0", "perm_gdl"});
    model.component("comp1").physics("fc").create("mem1", "Membrane", 2);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_r3_dom");
    model.component("comp1").physics("fc").feature("mem1").set("Psi_H2_mat", "userdef");
    model.component("comp1").physics("fc").feature("mem1").set("Psi_H2", "perm_H2");
    model.component("comp1").physics("fc").feature("icph1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("fc").feature("icph1")
         .set("sigmal", new String[]{"sigmal_mem", "0", "0", "0", "sigmal_mem", "0", "0", "0", "sigmal_mem"});
    model.component("comp1").physics("fc").create("icph2", "ElectrolytePhase", 2);
    model.component("comp1").physics("fc").feature("icph2").selection().set(2, 4);
    model.component("comp1").physics("fc").feature("icph2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("fc").feature("icph2")
         .set("sigmal", new String[]{"sigmal_ct", "0", "0", "0", "sigmal_ct", "0", "0", "0", "sigmal_ct"});
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(2);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(11);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_cell");
    model.component("comp1").physics("fc").feature("ecph1").create("inito2dom1", "InitialValuesO2Domains", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").selection().all();
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").set("initphis", "E_init");
    model.component("comp1").physics("fc").feature("h2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("h2gasph1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().set(13);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("x0N2dry", "xN2");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("T_hum", "T");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(7, 17);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(7, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "xN2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.79 0", 0);
    model.study("std1").feature("param").set("paramselect", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(E_init,-0.05,0.25)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 12, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"fc.phis"});
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"fc.Isx", "fc.Isy"});
    model.result("pg1").feature("arws1").set("arrowbase", "center");
    model.result("pg1").feature("arws1").set("color", "gray");
    model.result("pg1").feature("arws1").create("filt1", "Filter");
    model.result("pg1").feature("arws1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg1").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 12, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"fc.phil"});
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"fc.Ilx", "fc.Ily"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg2").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 12, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, O2 (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 12, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2O (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 12, 0);
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, N2 (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 12, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").label("\u538b\u529b (fc)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.p"});
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"fc.u", "fc.v"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "E_cell", 0);
    model.result("pg7").feature("glob1").set("xdataexpr", "I_cell_avg");
    model.result("pg7").feature("glob1").set("xdatadescr", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").feature("glob1").set("xdataunit", "A/cm^2");
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "Air", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "Oxygen", 1);
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("pemfc_parameter_estimation_air_data.csv");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("pemfc_parameter_estimation_o2_data.csv");
    model.result("pg7").run();
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("linestyle", "none");
    model.result("pg7").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg7").feature("tblp1").set("linemarker", "cycle");
    model.result("pg7").feature().duplicate("tblp2", "tblp1");
    model.result("pg7").run();
    model.result("pg7").feature("tblp2").set("table", "tbl2");
    model.result("pg7").feature("tblp2").set("linecolor", "cycle");
    model.result("pg7").run();

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("columnType", "col1", "value");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col1", "I_cell_avg");
    model.component("comp1").common("lso1").setEntry("unit", "col1", "A/cm^2");
    model.component("comp1").common("lso1").setEntry("columnType", "col2", "param");
    model.component("comp1").common("lso1").setEntry("parameterName", "col2", "E_cell");
    model.component("comp1").common("lso1").setEntry("parameterUnit", "col2", "V");
    model.component("comp1").common("lso1").setIndex("paramNames", "E_cell", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "E_cell", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "xN2", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", 0.79, 0);
    model.component("comp1").common().duplicate("lso2", "lso1");
    model.component("comp1").common("lso2").set("resultTable", "tbl2");
    model.component("comp1").common("lso2").setIndex("paramExprs", 0, 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "sigmal_mem", 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 1, 0);
    model.study("std2").feature("lsqo").setIndex("ubound", 20, 0);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "log10_i0_ref_ORR", 1);
    model.study("std2").feature("lsqo").setIndex("lbound", -6, 1);
    model.study("std2").feature("lsqo").setIndex("ubound", 0, 1);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "alphac_ORR", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 0.1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", 0.5, 2);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 2);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "E_cell", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "0.7[V]", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "ilim_ORR_ref", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1000, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", 1000, 3);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("lsqo").setIndex("ubound", 5000, 3);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("stat").set("lsqcont", true);
    model.study("std2").feature("stat").set("lsqpcontinuation", "E_cell");
    model.study("std2").feature("stat").set("initvallsq", "E_init");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.result("pg7").run();
    model.result("pg7").set("data", "dset3");
    model.result("pg7").run();
    model.result().move("pg7", 5);
    model.result().move("pg7", 4);
    model.result().move("pg7", 3);
    model.result().move("pg7", 2);
    model.result().move("pg7", 1);
    model.result().move("pg7", 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob1").active(false);
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "Air", 0);
    model.result("pg7").run();
    model.result("pg7").feature("tblp2").set("legend", true);
    model.result("pg7").feature("tblp2").set("legendmethod", "manual");
    model.result("pg7").feature("tblp2").setIndex("legends", "Oxygen", 0);
    model.result("pg7").run();
    model.result("pg7").set("showgrid", false);
    model.result("pg7").run();
    model.result("pg7").set("showgrid", true);
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("legend", false);
    model.result("pg7").run();
    model.result("pg7").feature("tblp2").set("legend", false);
    model.result("pg7").run();
    model.result("pg7").feature("glob1").active(true);

    model.title("\u805a\u5408\u7269\u819c\u71c3\u6599\u7535\u6c60\u6a21\u578b\u7684\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7a33\u6001\u6781\u5316\u6570\u636e\u5bf9\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u71c3\u6599\u7535\u6c60 (PEMFC) \u6a21\u578b\u8fdb\u884c\u53c2\u6570\u4f30\u8ba1\u3002\u8be5\u6a21\u578b\u5b9a\u4e49\u4e86\u4e00\u4e2a\u4e8c\u7ef4\u4e94\u5c42\u819c-\u7535\u6781-\u7ec4\u4ef6 (MEA)\uff0c\u5176\u4e2d\u5305\u542b\u7535\u5b50\u548c\u79bb\u5b50\u7535\u8377\u5e73\u8861\u3001Butler-Volmer \u52a8\u529b\u5b66\uff0c\u4ee5\u53ca\u6c27\u6c14/\u7a7a\u6c14\u6c14\u4f53\u6269\u6563\u5c42\u548c\u7535\u6781\uff08\u50ac\u5316\u5c42\uff09\u4e2d\u7684\u6c14\u4f53\u6269\u6563\u548c\u5bf9\u6d41\u4f20\u9012\u3002\n\n\u4e3a\u4e86\u4f30\u8ba1\u6a21\u578b\u7684\u56db\u4e2a\u53c2\u6570\uff0c\u5176\u4e2d\u4f7f\u7528\u4e86\u4e24\u7ec4\u6781\u5316\u6570\u636e\uff0c\u5206\u522b\u5bf9\u5e94\u6e7f\u6da6\u6c27\u6c14\u6216\u7a7a\u6c14\u6df7\u5408\u7269\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("pemfc_parameter_estimation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
