/*
 * brownian_motion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:56 by COMSOL 6.3.0.290. */
public class brownian_motion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.param().set("router", "0.0005");
    model.param().descr("router", "\u5916\u534a\u5f84");
    model.param().set("rinner", "0.00025");
    model.param().descr("rinner", "\u5185\u534a\u5f84");
    model.param().set("rp", "1E-7[m]");
    model.param().descr("rp", "\u9897\u7c92\u534a\u5f84");
    model.param().set("T", "300[K]");
    model.param().descr("T", "\u6e29\u5ea6");
    model.param().set("eta", "2E-5[Pa*s]");
    model.param().descr("eta", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("D", "k_B_const*T/(6*pi*eta*rp)");
    model.param().descr("D", "\u6269\u6563\u7cfb\u6570");
    model.param().set("ds", "1");
    model.param().descr("ds", "\u968f\u673a\u6570\u751f\u6210\u5668\u7684\u8f93\u5165");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "router");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "rinner");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("smooth", "2E-7");
    model.component("comp1").variable("var1").descr("smooth", "\u5e73\u6ed1\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("xd", "x[1/m]");
    model.component("comp1").variable("var1").descr("xd", "x \u5750\u6807");
    model.component("comp1").variable("var1").set("yd", "y[1/m]");
    model.component("comp1").variable("var1").descr("yd", "y \u5750\u6807");
    model.component("comp1").variable("var1").set("c0", "1");
    model.component("comp1").variable("var1").descr("c0", "\u5cf0\u521d\u59cb\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("c_init", "2*c0*(1-flc2hs(xd^2+yd^2-smooth^2,5e-11))");
    model.component("comp1").variable("var1").descr("c_init", "\u521d\u59cb\u6d53\u5ea6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().set(2);

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(1, 2, 5, 8);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_init", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "6.7E-5*0.01");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 100");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1E-4");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1, 2);
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "intop1(c)/(intop1(c)+intop2(c))", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/fpt", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/tds", false);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").prop("WallAccuracyOrder").setIndex("WallAccuracyOrder", 1, 0);
    model.component("comp1").physics("fpt").prop("RandomNumberArgs").setIndex("RandomNumberArgs", "UserDefined", 0);
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "2*rp");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("mu_mat", "userdef");
    model.component("comp1").physics("fpt").feature("df1").set("mu", "eta");
    model.component("comp1").physics("fpt").create("bf1", "BrownianForce", 2);
    model.component("comp1").physics("fpt").feature("bf1").selection().all();
    model.component("comp1").physics("fpt").feature("bf1").set("minput_temperature", "T");
    model.component("comp1").physics("fpt").feature("bf1").set("mu_mat", "userdef");
    model.component("comp1").physics("fpt").feature("bf1").set("mu", "eta");
    model.component("comp1").physics("fpt").feature("bf1").set("i", "ds");
    model.component("comp1").physics("fpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("fpt").feature("relg1").set("InitialVelocity", "ConstantSpeedSpherical");
    model.component("comp1").physics("fpt").feature("relg1").set("Us", 0);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("Nvel", 5000, 0);
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().set(1);
    model.component("comp1").physics("fpt").feature("pcnt1").set("ReleaseFeature", "relg1");

    model.study("std2").feature("time").set("tlist", "0 100");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1E-2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "part1");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg2").create("traj1", "ParticleTrajectories");
    model.result("pg2").feature("traj1").set("pointtype", "point");
    model.result("pg2").feature("traj1").set("linetype", "none");
    model.result("pg2").feature("traj1").create("col1", "Color");
    model.result("pg2").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg2").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").set("expr", new String[]{"fpt.pcnt1.alpha"});
    model.result().numerical("gev2").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev2").set("unit", new String[]{"1"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/tds", false);
    model.study("std3").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std3").feature("time").set("tlist", "0 100");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", "1E-2");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "router", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "router", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "ds", 0);
    model.study("std3").feature("param").setIndex("plistarr", "2 3 4 5", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol4");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_fpt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "fpt");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "part2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (fpt) 1");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg3").run();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "dset4");
    model.result().numerical("gev3").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev3").set("tablecols", "level1");
    model.result().numerical("gev3").set("expr", new String[]{"fpt.pcnt1.alpha"});
    model.result().numerical("gev3").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev3").set("unit", new String[]{"1"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").run();

    model.title("\u5e03\u6717\u8fd0\u52a8");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u6a21\u62df\u5e03\u6717\u8fd0\u52a8\u529b\u5f15\u8d77\u7684\u9897\u7c92\u8fd0\u52a8\uff0c\u5c06\u7ed3\u679c\u4e0e\u7eaf\u6269\u6563\u65b9\u7a0b\u7684\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u4e24\u8005\u975e\u5e38\u4e00\u81f4\u3002\u6700\u540e\u6267\u884c\u4e86\u591a\u6b21\u8499\u7279\u5361\u7f57\u4eff\u771f\u8ba1\u7b97\uff0c\u5176\u4e2d\u6bcf\u6b21\u4f7f\u7528\u4e00\u7ec4\u4e0d\u540c\u7684\u968f\u673a\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("brownian_motion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
