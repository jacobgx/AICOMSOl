/*
 * isoelectric_focusing_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:54 by COMSOL 6.3.0.290. */
public class isoelectric_focusing_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("el", "ElectrophoreticTransport", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/el", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/el", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("mob", "3e-8[m^2/V/s]/F_const");
    model.param().set("pI_start", "3.5");
    model.param().set("L", "5[cm]");
    model.param().set("dV", "-300[V/cm]");
    model.param().set("c0", "5.0[mM]");
    model.param().set("T", "25[degC]");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("el").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("el").feature("eip1").selection().set(1);
    model.component("comp1").physics("el").create("eip2", "ElectrolytePotential", 0);
    model.component("comp1").physics("el").feature("eip2").selection().set(2);
    model.component("comp1").physics("el").feature("eip2").set("philbnd", "dV*L");
    model.component("comp1").physics("el").create("amph1", "Ampholyte", 1);
    model.component("comp1").physics("el").feature("amph1").setIndex("pKa", "pI_start-1", 0, 0);
    model.component("comp1").physics("el").feature("amph1").setIndex("pKa", "pI_start+1", 1, 0);
    model.component("comp1").physics("el").feature("amph1").set("um", "mob");
    model.component("comp1").physics("el").feature("amph1").feature("initc1").set("initc", "c0");
    model.component("comp1").physics("el").feature().duplicate("amph2", "amph1");
    model.component("comp1").physics("el").feature("amph2").setIndex("pKa", "pI_start-1+1", 0, 0);
    model.component("comp1").physics("el").feature("amph2").setIndex("pKa", "pI_start+1+1", 1, 0);
    model.component("comp1").physics("el").feature().duplicate("amph3", "amph2");
    model.component("comp1").physics("el").feature("amph3").setIndex("pKa", "pI_start-1+2", 0, 0);
    model.component("comp1").physics("el").feature("amph3").setIndex("pKa", "pI_start+1+2", 1, 0);
    model.component("comp1").physics("el").feature().duplicate("amph4", "amph3");
    model.component("comp1").physics("el").feature("amph4").setIndex("pKa", "pI_start-1+3", 0, 0);
    model.component("comp1").physics("el").feature("amph4").setIndex("pKa", "pI_start+1+3", 1, 0);
    model.component("comp1").physics("el").feature().duplicate("amph5", "amph4");
    model.component("comp1").physics("el").feature("amph5").setIndex("pKa", "pI_start-1+4", 0, 0);
    model.component("comp1").physics("el").feature("amph5").setIndex("pKa", "pI_start+1+4", 1, 0);
    model.component("comp1").physics("el").feature().duplicate("amph6", "amph5");
    model.component("comp1").physics("el").feature("amph6").setIndex("pKa", "pI_start-1+5", 0, 0);
    model.component("comp1").physics("el").feature("amph6").setIndex("pKa", "pI_start+1+5", 1, 0);
    model.component("comp1").physics("el").feature().duplicate("amph7", "amph6");
    model.component("comp1").physics("el").feature("amph7").setIndex("pKa", "pI_start-1+6", 0, 0);
    model.component("comp1").physics("el").feature("amph7").setIndex("pKa", "pI_start+1+6", 1, 0);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "4e-6");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-6");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").set("bndsnap1", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "-el.nIl");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descractive", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u63a2\u9488");

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "0 0.4 4");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "1e-5");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"el.pH"});
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").label("pH (el)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"el.sigmal"});
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u5bfc\u7387 (el)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phil"});
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d (el)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"el.c_S"});
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").label("\u6469\u5c14\u6d53\u5ea6 - S (el)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"el.c_S1"});
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").label("\u6469\u5c14\u6d53\u5ea6 - S1 (el)");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"el.c_S2"});
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").label("\u6469\u5c14\u6d53\u5ea6 - S2 (el)");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"el.c_S3"});
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").label("\u6469\u5c14\u6d53\u5ea6 - S3 (el)");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1);
    model.result("pg9").feature("lngr1").set("expr", new String[]{"el.c_S4"});
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").label("\u6469\u5c14\u6d53\u5ea6 - S4 (el)");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "x");
    model.result("pg10").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg10").feature("lngr1").selection().set(1);
    model.result("pg10").feature("lngr1").set("expr", new String[]{"el.c_S5"});
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").label("\u6469\u5c14\u6d53\u5ea6 - S5 (el)");
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "x");
    model.result("pg11").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr1").selection().set(1);
    model.result("pg11").feature("lngr1").set("expr", new String[]{"el.c_S6"});
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").label("\u6469\u5c14\u6d53\u5ea6 - S6 (el)");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6d41\u5bc6\u5ea6\u4e0e\u65f6\u95f4\u7684\u5173\u7cfb");
    model.result("pg1").set("showlegends", false);
    model.result("pg5").run();
    model.result().duplicate("pg12", "pg5");
    model.result("pg12").run();
    model.result("pg12").label("\u6d53\u5ea6");
    model.result("pg12").setIndex("looplevelinput", "last", 0);
    model.result("pg12").set("titletype", "none");
    model.result("pg12").run();
    model.result("pg12").feature("lngr1").set("legend", false);
    model.result("pg12").feature().duplicate("lngr2", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("expr", "el.c_S1");
    model.result("pg12").feature().duplicate("lngr3", "lngr2");
    model.result("pg12").run();
    model.result("pg12").feature("lngr3").set("expr", "el.c_S2");
    model.result("pg12").feature().duplicate("lngr4", "lngr3");
    model.result("pg12").run();
    model.result("pg12").feature("lngr4").set("expr", "el.c_S3");
    model.result("pg12").feature().duplicate("lngr5", "lngr4");
    model.result("pg12").run();
    model.result("pg12").feature("lngr5").set("expr", "el.c_S4");
    model.result("pg12").feature().duplicate("lngr6", "lngr5");
    model.result("pg12").run();
    model.result("pg12").feature("lngr6").set("expr", "el.c_S5");
    model.result("pg12").feature().duplicate("lngr7", "lngr6");
    model.result("pg12").run();
    model.result("pg12").feature("lngr7").set("expr", "el.c_S6");
    model.result("pg12").run();

    model.title("\u7b49\u7535\u805a\u7126");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u67f1\u4e2d\u4e03\u79cd\u4e24\u6027\u7535\u89e3\u8d28\u7684\u7b49\u7535\u805a\u7126 (IEF)\u3002\n\n\u8fd9\u662f\u4e00\u4e2a\u901a\u8fc7\u77ac\u6001\u4eff\u771f\u6c42\u89e3\u7684\u4e00\u7ef4\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("isoelectric_focusing_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
