/*
 * electrochemical_capacitor_porous_electrodes.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class electrochemical_capacitor_porous_electrodes {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Electrochemical_Capacitors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_sep", "25[um]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("L_elec", "50[um]", "\u7535\u6781\u957f\u5ea6");
    model.param().set("kappa_l", "0.0185[S/cm/M]*c_bulk", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("sigma_s", "0.0521[S/m]", "\u7535\u5bfc\u7387");
    model.param().set("eps_el", "0.67", "\u7535\u6781\u7684\u5b54\u9699\u7387");
    model.param().set("eps_sep", "0.6", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("c_bulk", "0.93[M]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("T", "25[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("tau_sep", "1.29", "\u9694\u819c\u7684\u8fc2\u66f2\u5ea6");
    model.param().set("tau_electrode", "1", "\u7535\u6781\u7684\u8fc2\u66f2\u5ea6");
    model.param().set("aC", "42[F/cm^3]", "\u9762\u79ef\u6bd4\u7535\u5bb9");
    model.param().set("D", "kappa_l*(R_const*T)/F_const^2/c_bulk/2", "\u6269\u6563\u7cfb\u6570");
    model.param().set("Av", "1[cm^2/cm^3]", "\u6bd4\u9762\u79ef");
    model.param().set("Cap", "3500[F]", "\u6807\u79f0\u7535\u5bb9");
    model.param().set("A_cell_nom", "2*Cap/(aC*L_elec)", "\u57fa\u4e8e\u6807\u79f0\u5bb9\u91cf\u7684\u9762\u79ef");
    model.param().set("A_cell", "A_cell_nom", "\u7535\u5316\u5b66\u7535\u6c60\u7684\u9762\u79ef");
    model.param().set("tau_electrolyte", "2.3", "\u7535\u89e3\u8d28\u7684\u8fc2\u66f2\u5ea6");
    model.param().set("Cdl", "aC/Av", "\u9762\u79ef\u6bd4\u7535\u5bb9");
    model.param().label("\u53c2\u6570\uff1a\u7535\u5316\u5b66\u7535\u6c60");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u8d1f\u8f7d\u66f2\u7ebf");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("V_rest", "-2.5[V]", "\u5916\u52a0\u529f\u7387\u7684\u9759\u6001\u7535\u52bf");
    model.param("par2").set("P_app", "0.85[kW]", "\u5916\u52a0\u529f\u7387");
    model.param("par2").set("i_app", "100[A]", "\u5916\u52a0\u7535\u6d41");
    model.param("par2").set("V_min", "1.4[V]", "\u6700\u5c0f\u7535\u6c60\u7535\u538b");
    model.param("par2").set("V_init", "0[V]", "\u521d\u59cb\u7535\u52bf");
    model.param("par2").set("t_cv", "5[s]", "\u6052\u538b\u5de5\u4f5c\u65f6\u95f4");
    model.param("par2").set("t_rest", "180[s]", "\u9759\u7f6e\u65f6\u95f4");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_elec", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_elec", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c1", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c2", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").feature("init1").set("initphis", "V_init");
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("tcd").feature("pce1").selection().set(1, 3);
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_c1", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_c2", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("tcd").feature("pce1").set("epss", "1-eps_el");
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "eps_el");
    model.component("comp1").physics("tcd").feature("pce1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("tcd").feature("pce1").set("taul", "tau_electrolyte");
    model.component("comp1").physics("tcd").feature("pce1").set("ElectricCorrModel", "Tortuosity");
    model.component("comp1").physics("tcd").feature("pce1").set("taus", "tau_electrode");
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "c1_q", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").set("AddVolumeChangeToElectrodeVolumeFraction", false);
    model.component("comp1").physics("tcd").feature("pce1")
         .set("SubtractVolumeChangeFromElectrolyteVolumeFraction", false);
    model.component("comp1").physics("tcd").feature("pce1").create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").set("Cdl", "Cdl");
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").set("av_dl", "Av");
    model.component("comp1").physics("tcd").feature("pce1").feature("pdl1").setIndex("Vi0", 0.5, 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").active(false);
    model.component("comp1").physics("tcd").feature("pce1").create("nfr1", "NonFaradaicReactions", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr1").setIndex("R", "tcd.ivdl/F_const", 0, 0);
    model.component("comp1").physics("tcd").create("sep1", "Separator", 1);
    model.component("comp1").physics("tcd").feature("sep1").selection().set(2);
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_c1", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_c2", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("sep1").set("epsl", "eps_sep");
    model.component("comp1").physics("tcd").feature("sep1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("tcd").feature("sep1").set("taul", "tau_sep");
    model.component("comp1").physics("tcd").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("tcd").feature("egnd1").selection().set(1);
    model.component("comp1").physics("tcd").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("tcd").feature("ecd1").selection().set(4);
    model.component("comp1").physics("tcd").feature("ecd1").set("nis", "i_app_ch/A_cell");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl("intop1").set("opname", "right_el");

    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "V_max", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 1.8, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimDescr", "\u6700\u5927\u7535\u538b", 0, 0);
    model.component("comp1").physics("ev").create("es1", "EventSequence", -1);
    model.component("comp1").physics("ev").feature("es1").label("\u5145\u653e\u7535\u5faa\u73af");
    model.component("comp1").physics("ev").feature("es1").set("loop", true);
    model.component("comp1").physics("ev").feature("es1").feature("sm1").label("\u6052\u5b9a\u7535\u6d41");
    model.component("comp1").physics("ev").feature("es1").feature("sm1").set("stateName", "CC");
    model.component("comp1").physics("ev").feature("es1").feature("sm1")
         .set("condition", "comp1.right_el(phis)-V_max[V]");
    model.component("comp1").physics("ev").feature("es1").create("sm2", "SequenceMember", -1);
    model.component("comp1").physics("ev").feature("es1").feature("sm2").label("\u6052\u538b");
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("stateName", "CV");
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("endConditionOptions", "duration");
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("duration", "t_cv");
    model.component("comp1").physics("ev").feature("es1").create("sm3", "SequenceMember", -1);
    model.component("comp1").physics("ev").feature("es1").feature("sm3").label("\u9759\u606f");
    model.component("comp1").physics("ev").feature("es1").feature("sm3").set("stateName", "REST");
    model.component("comp1").physics("ev").feature("es1").feature("sm3").set("endConditionOptions", "duration");
    model.component("comp1").physics("ev").feature("es1").feature("sm3").set("duration", "t_rest");
    model.component("comp1").physics("ev").feature("es1").feature("sm3").setIndex("reInitName", "V_max", 0, 0);
    model.component("comp1").physics("ev").feature("es1").feature("sm3").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("es1").feature("sm3").setIndex("reInitValue", "V_max+0.2", 0, 0);
    model.component("comp1").physics("tcd").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("tcd").feature("ge1").setIndex("name", "i_app_ch", 0, 0);
    model.component("comp1").physics("tcd").feature("ge1")
         .setIndex("equation", "(CC==1)*(i_app_ch-i_app)/i_app+(CV==1)*(right_el(phis)-V_min)/V_min+(REST==1)*(i_app_ch)/i_app", 0, 0);
    model.component("comp1").physics("tcd").feature("ge1").setIndex("initialValueU", "i_app", 0, 0);
    model.component("comp1").physics("tcd").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("tcd").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("tcd").feature("ge1").setIndex("CustomDependentVariableUnit", "A", 0, 0);

    model.nodeGroup().create("grp1", "Physics", "tcd");
    model.nodeGroup("grp1").placeAfter("egnd1");
    model.nodeGroup("grp1").add("ecd1");
    model.nodeGroup("grp1").add("ge1");
    model.nodeGroup("grp1").label("\u6052\u5b9a\u7535\u6d41\u5145\u7535/\u6052\u538b\u653e\u7535");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1aCC \u5145\u7535 CV \u653e\u7535");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "0 40");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scaleval", "i_app");
    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("eventtol", 0.001);
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1")
         .set("eventstopName", new String[]{"comp1.ev.es1.sm1.event", "comp1.ev.es1.sm2.event", "comp1.ev.es1.sm3.event"});
    model.sol("sol1").feature("t1").feature("st1").set("eventstopActive", new String[]{"off", "off", "off"});
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.V_max>2.45", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("CC \u5145\u7535-CV \u653e\u7535\u7684\u7535\u538b\u548c\u7535\u6d41\u66f2\u7ebf");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("\u7535\u6d41");
    model.result("pg1").feature("glob1").setIndex("expr", "comp1.i_app_ch", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "\u7535\u6c60\u7535\u6d41", 0);
    model.result("pg1").feature("glob1").set("xdataparamunit", "s");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").label("\u7535\u4f4d");
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "phis");
    model.result("pg1").feature("ptgr1").set("descractive", true);
    model.result("pg1").feature("ptgr1").set("descr", "\u7535\u6c60\u7535\u4f4d");
    model.result("pg1").feature("ptgr1").set("xdataparamunit", "s");
    model.result("pg1").feature("ptgr1").set("linestyle", "dashdot");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("autopoint", false);
    model.result("pg1").feature("ptgr1").set("autodescr", true);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "t-t <sub>CCstart</sub>");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2")
         .label("CC \u5145\u7535-CV \u653e\u7535\u7684\u7535\u538b\u548c\u7535\u6d41\u66f2\u7ebf\uff08\u91cd\u53e0\uff09");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "t-t_CC_start");
    model.result("pg2").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("filt1").set("expr", "(REST!=1)&&(V_max>1.8)");
    model.result("pg2").feature("glob1").feature("filt1").set("xdec", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("col1").set("expr", "V_max");
    model.result("pg2").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "t-t_CC_start");
    model.result("pg2").feature("ptgr1").create("filt1", "PointGraphFilter");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").feature("filt1").set("expr", "(REST!=1)&&(V_max>1.8)");
    model.result("pg2").feature("ptgr1").feature("filt1").set("xdec", false);
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").feature("col1").set("expr", "V_max");
    model.result("pg2").feature("ptgr1").feature("col1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 40);
    model.result("pg2").set("ymin", 0);
    model.result("pg2").set("ymax", 2.6);
    model.result("pg2").set("yminsec", -2000);
    model.result("pg2").set("ymaxsec", 300);
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();

    model.component("comp1").physics("tcd").create("epow1", "ElectrodePower", 0);
    model.component("comp1").physics("tcd").feature("epow1").selection().set(4);
    model.component("comp1").physics("tcd").feature("epow1").set("ElectrodePowerType", "AveragePowerDensity");
    model.component("comp1").physics("tcd").feature("epow1").set("Pas", "-P_app/A_cell");
    model.component("comp1").physics("tcd").feature("epow1").set("phis0init", "V_rest");
    model.component("comp1").physics("tcd").create("init2", "init", 1);
    model.component("comp1").physics("tcd").feature("init2").selection().set(3);
    model.component("comp1").physics("tcd").feature("init2").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").feature("init2").set("initphis", "V_rest");
    model.component("comp1").physics("tcd").feature("init2").label("\u521d\u59cb\u503c - \u6052\u5b9a\u529f\u7387");

    model.nodeGroup().create("grp2", "Physics", "tcd");
    model.nodeGroup("grp2").placeAfter("egnd1");
    model.nodeGroup("grp2").add("epow1");
    model.nodeGroup("grp2").add("init2");
    model.nodeGroup("grp2").label("\u6052\u5b9a\u7535\u6d41\u5145\u7535");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"tcd/ecd1", "tcd/ge1"});
    model.study("std2").feature("time").setSolveFor("/physics/ev", false);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"tcd/ecd1", "tcd/ge1", "ev"});
    model.study("std2").feature("time").set("tlist", "0 2");
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "A_cell", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m^2", 0);
    model.study("std2").feature("time").setIndex("pname", "A_cell", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m^2", 0);
    model.study("std2").feature("time").setIndex("pname", "A_cell_nom", 1);
    model.study("std2").feature("time").setIndex("plistarr", "", 1);
    model.study("std2").feature("time").setIndex("punit", "m^2", 1);
    model.study("std2").feature("time").setIndex("pname", "A_cell_nom", 1);
    model.study("std2").feature("time").setIndex("plistarr", "", 1);
    model.study("std2").feature("time").setIndex("punit", "m^2", 1);
    model.study("std2").feature("time").setIndex("pname", "P_app", 0);
    model.study("std2").feature("time").setIndex("plistarr", "0.5[kW] 0.85[kW]", 0);
    model.study("std2").feature("time").setIndex("pname", "V_rest", 1);
    model.study("std2").feature("time").setIndex("plistarr", "-2.1[V] -2.5[V]", 1);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u6052\u5b9a\u529f\u7387\u5145\u7535");
    model.study("std2").setGenPlots(false);

    model.sol("sol2").feature("t1").set("tout", "tsteps");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53cc\u7535\u5c42\u7535\u6d41");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1, 3);
    model.result("pg3").feature("lngr1").set("data", "dset2");
    model.result("pg3").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg3").feature("lngr1").set("expr", "tcd.ivdl");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x/(L_elec+L_sep+L_elec)");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("xdatadescractive", true);
    model.result("pg3").feature("lngr1").set("xdatadescr", "\u65e0\u91cf\u7eb2\u957f\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1")
         .setIndex("legends", "60: t=2 s, P<sub>app</sub>=500 W, V<sub>rest</sub>=-2.1 V", 0);
    model.result("pg3").feature("lngr1")
         .setIndex("legends", "125: t=2 s, P<sub>app</sub>=850 W, V<sub>rest</sub>=-2.5 V", 1);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5145\u7535\u529f\u7387\u7684\u7535\u8377\u5bc6\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "-tcd.c_pce1_c1_q*F_const");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "\u7535\u8377\u5bc6\u5ea6");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x/(L_sep+2*L_elec)");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1")
         .setIndex("legends", "60: t=2 s, P<sub>app</sub>=500 W, V<sub>rest</sub>=-2.1 V", 0);
    model.result("pg4").feature("lngr1")
         .setIndex("legends", "125: t=2 s, P<sub>app</sub>=850 W, V<sub>rest</sub>=-2.5 V", 1);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65e0\u91cf\u7eb2\u957f\u5ea6 (1)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5")
         .label("\u6052\u5b9a\u529f\u7387\u5145\u7535\u7684\u7535\u538b\u548c\u7535\u6d41\u66f2\u7ebf");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").label("\u7535\u6d41");
    model.result("pg5").feature("ptgr1").selection().set(4);
    model.result("pg5").feature("ptgr1").set("expr", "right_el(tcd.nIs)*A_cell");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("linecolor", "cyclereset");
    model.result("pg5").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg5").feature("ptgr1").set("legendpattern", "\u7535\u6d41 (P = eval(P_app) kW)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").label("\u7535\u4f4d");
    model.result("pg5").feature("glob1").set("expr", new String[]{"tcd.phis0_epow1"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg5").feature("glob1").set("linestyle", "dashdot");
    model.result("pg5").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg5").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg5").feature("glob1").set("legendpattern", "\u7535\u4f4d (P = eval(P_app) kW)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").set("yseclabelactive", true);
    model.result("pg5").set("yseclabel", "\u7535\u4f4d (V)");
    model.result("pg5").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymin", 200.79068524910107);
    model.result("pg5").set("ymax", 340.58128603840646);
    model.result("pg5").run();
    model.result("pg5").set("ymaxsec", -0.45314923375307226);
    model.result("pg5").set("yminsec", -3);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "center");
    model.result("pg5").run();

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"tcd/epow1", "tcd/init2"});
    model.study("std1").feature("time").set("plot", true);

    model.title("\u5177\u6709\u591a\u5b54\u7535\u6781\u7684\u7535\u5316\u5b66\u7535\u5bb9\u5668");

    model
         .description("\u7535\u5316\u5b66\u8d85\u7ea7\u7535\u5bb9\u5668\u76f8\u5bf9\u4e8e\u4f20\u7edf\u7535\u5bb9\u5668\uff0c\u5177\u6709\u66f4\u9ad8\u7684\u80fd\u91cf\u5bc6\u5ea6\uff0c\u4ee5\u53ca\u5145\u7535\u901f\u5ea6\u5feb\u3001\u5145\u653e\u7535\u5468\u671f\u957f\u3001\u5de5\u4f5c\u6e29\u5ea6\u8303\u56f4\u5e7f\u7b49\u4f18\u70b9\uff0c\u56e0\u6b64\u5728\u6df7\u5408\u52a8\u529b\u6c7d\u8f66\u9886\u57df\u5f97\u5230\u4e86\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\n\n\u8fd9\u4e2a\u4e00\u7ef4\u6559\u7a0b\u6a21\u62df\u7535\u5316\u5b66\u7535\u5bb9\u5668\u4e2d\u591a\u5b54\u7535\u6781\u7684\u7535\u6d41\u5206\u5e03\u548c\u7535\u6781\u5229\u7528\u7387\uff1b\u5e76\u4f7f\u7528 Nernst-Planck \u65b9\u7a0b\u6765\u6a21\u62df\u4e8c\u5143\u7535\u89e3\u8d28\u7684\u6269\u6563\u548c\u8fc1\u79fb\u4f20\u9012\uff0c\u5176\u4e2d\u4f7f\u7528\u8fc2\u66f2\u5ea6\u548c\u5b54\u9699\u7387\u53c2\u6570\u6765\u63a8\u5bfc\u591a\u5b54\u7535\u6781\u7684\u6709\u6548\u4f20\u9012\u53c2\u6570\u3002\u672c\u4f8b\u4f7f\u7528\u6052\u6d41-\u6052\u538b\u548c\u6052\u529f\u7387\u5145\u653e\u7535\u8d1f\u8f7d\u8fdb\u884c\u4eff\u771f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electrochemical_capacitor_porous_electrodes.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
