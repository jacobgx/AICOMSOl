/*
 * resistive_wafer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:58 by COMSOL 6.3.0.290. */
public class resistive_wafer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");
    model.component("comp1").physics().create("els", "ElectrodeShell", "geom1");
    model.component("comp1").physics("els").field("electricpotential").field("phis_wafer");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/els", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);
    model.study("std1").feature("time").setSolveFor("/physics/els", true);

    model.component("comp1").geom("geom1").insertFile("resistive_wafer_geom_sequence.mph", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("sigma", "20[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("i0_cathode", "0.1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("Eeq_cathode", "0.2[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u9634\u6781");
    model.param().set("T", "293.15[K]", "\u6e29\u5ea6");
    model.param().set("alpha_a", "0.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u9634\u6781");
    model.param().set("alpha_c", "0.5", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u9634\u6781");
    model.param().set("w", "5.6e7[S/m]", "\u7535\u5bfc\u7387\uff0c\u6c89\u79ef\u5c42");
    model.param().set("rho", "8e6[g/m^3]", "\u91d1\u5c5e\u6c89\u79ef\u5bc6\u5ea6");
    model.param().set("M", "65[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u91d1\u5c5e\u6c89\u79ef");
    model.param().set("s_init", "0.1e-6[m]", "\u521d\u59cb\u91d1\u5c5e\u5c42\u539a\u5ea6");
    model.param().set("I_cell", "0.4[A]", "\u7535\u9540\u6c60\u7535\u6d41");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(15, 16, 17);
    model.component("comp1").selection("sel1").label("\u6676\u7247\u8868\u9762");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(17);
    model.component("comp1").selection("sel2").label("\u9634\u6781");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(6);
    model.component("comp1").selection("sel3").label("\u9633\u6781");

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("ic1", "ElectrolyteCurrent", 2);
    model.component("comp1").physics("cd").feature("ic1").selection().named("sel3");
    model.component("comp1").physics("cd").feature("ic1").set("Itl", "I_cell");
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().named("sel2");
    model.component("comp1").physics("cd").feature("es1").set("phisext0", "phis_wafer");
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", "rho", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", "M", 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_cathode");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_cathode");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphac", "alpha_c");
    model.component("comp1").physics("cd").feature("init1").set("phil", "-Eeq_cathode");
    model.component("comp1").physics("els").selection().named("sel1");
    model.component("comp1").physics("els").create("depe1", "DepositingElectrode", 2);
    model.component("comp1").physics("els").feature("depe1").selection().named("sel2");
    model.component("comp1").physics("els").feature("depe1").set("s0", "s_init");
    model.component("comp1").physics("els").feature("depe1").set("deltas_src", "root.comp1.cd.sbtot");
    model.component("comp1").physics("els").feature("depe1").set("sigma_mat", "userdef");
    model.component("comp1").physics("els").feature("depe1")
         .set("sigma", new String[]{"w", "0", "0", "0", "w", "0", "0", "0", "w"});
    model.component("comp1").physics("els").feature("depe1").set("in_src", "root.comp1.cd.es1.er1.iloc");
    model.component("comp1").physics("els").feature("ece1").set("s", "s_init");
    model.component("comp1").physics("els").feature("ece1").set("sigma_mat", "userdef");
    model.component("comp1").physics("els").feature("ece1")
         .set("sigma", new String[]{"w", "0", "0", "0", "w", "0", "0", "0", "w"});
    model.component("comp1").physics("els").create("gnd1", "Ground", 1);
    model.component("comp1").physics("els").feature("gnd1").selection().set(34);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("time").set("tlist", "range(0,20,600)");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("arwv1").set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf");
    model.result("pg1").feature("arwv1").set("titletype", "none");
    model.result("pg1").feature("arwv1").set("xnumber", 1);
    model.result("pg1").feature("arwv1").set("ynumber", 13);
    model.result("pg1").feature("arwv1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").create("slc2", "Slice");
    model.result("pg1").feature("slc2").set("titletype", "none");
    model.result("pg1").feature("slc2").set("quickplane", "xy");
    model.result("pg1").feature("slc2").set("quickzmethod", "coord");
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("sel1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "phis_wafer");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "cd.itot");
    model.result("pg3").feature("surf1").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "els.deltas");
    model.result("pg4").feature("surf1").set("descr", "\u7535\u6781\u539a\u5ea6\u53d8\u5316");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"", "", "els.deltas"});
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 5000);
    model.result("pg4").run();

    model.sol("sol1").copySolution("sol3");

    model.component("comp1").selection("sel2").set(15, 17);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -30, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 30, 1, 1);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset4");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "cln1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "cln1");
    model.result("pg5").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr1").setIndex("looplevel", new int[]{31}, 0);
    model.result("pg5").feature("lngr1").set("expr", "els.deltas");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "t = 600 s\uff0c\u4f7f\u7528\u5206\u6d41\u5668", 0);
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "y");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("data", "cln2");
    model.result("pg5").feature("lngr2").setIndex("looplevel", new int[]{24}, 0);
    model.result("pg5").feature("lngr2").set("titletype", "none");
    model.result("pg5").feature("lngr2")
         .setIndex("legends", "t = 460 s\uff0c\u4e0d\u4f7f\u7528\u5206\u6d41\u5668", 0);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u6d41\u548c\u7535\u4f4d");
    model.result("pg2").run();
    model.result("pg2").label("\u91d1\u5c5e\u6c89\u79ef\u7269\u4e2d\u7684\u7535\u4f4d");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").run();
    model.result("pg4").label("\u91d1\u5c5e\u539a\u5ea6\u53d8\u5316");
    model.result("pg5").run();
    model.result("pg5").label("\u6c89\u79ef\u7269\u539a\u5ea6\u6bd4\u8f83");

    model.title("\u6297\u8680\u56fe\u6848\u5316\u6676\u7247\u4e0a\u7684\u7535\u9540");

    model
         .description("\u672c\u4f8b\u6a21\u62df CUP-PLATER \u53cd\u5e94\u5668\u4e2d\u7535\u963b\u6676\u7247\u4e0a\u7684\u77ac\u6001\u94dc\u6c89\u79ef\u3002\u968f\u7740\u6c89\u79ef\u5c42\u7684\u52a0\u539a\uff0c\u5176\u7535\u963b\u635f\u8017\u9010\u6e10\u51cf\u5c0f\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5206\u6d41\u7535\u6781\u5f97\u5230\u66f4\u5747\u5300\u7684\u6c89\u79ef\u539a\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("resistive_wafer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
