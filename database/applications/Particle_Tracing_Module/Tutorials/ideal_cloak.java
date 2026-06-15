/*
 * ideal_cloak.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:55 by COMSOL 6.3.0.290. */
public class ideal_cloak {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pt", true);

    model.param().set("L", "1[m]");
    model.param().descr("L", "\u6846\u957f\u5ea6");
    model.param().set("a", "0.2[m]");
    model.param().descr("a", "\u5185\u534a\u5f84");
    model.param().set("b", "0.4[m]");
    model.param().descr("b", "\u5916\u534a\u5f84");
    model.param().set("n_air", "1");
    model.param().descr("n_air", "\u7a7a\u6c14\u7684\u6298\u5c04\u7387");
    model.param().set("hmax", "L*0.2");
    model.param().descr("hmax", "\u4f53\u5185\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param().set("hmax_cloak", "b*0.05");
    model.param().descr("hmax_cloak", "\u6597\u7bf7\u5185\u6700\u5927\u5355\u5143\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*L", "2*L", "2*L"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "a");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("r", "b");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2+z^2+eps)", "\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1")
         .set("rh", "sqrt(x^2+y^2+eps)", "xy \u5e73\u9762\u7684\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1").set("dr", "1e-2*b[1/m]", "\u6298\u5c04\u7387\u5e73\u6ed1\u51fd\u6570");
    model.component("comp1").variable("var1").set("cos_theta", "z/r");
    model.component("comp1").variable("var1").set("sin_theta", "rh/r");
    model.component("comp1").variable("var1")
         .set("pr", "px*sin_theta*cos_phi+py*sin_theta*sin_phi+pz*cos_theta", "\u6ce2\u77e2\uff0cr \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("p_theta", "px*cos_theta*cos_phi+py*cos_theta*sin_phi-pz*sin_theta", "\u6ce2\u77e2\uff0ctheta \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("p_phi", "-px*sin_phi+py*cos_phi", "\u6ce2\u77e2\uff0cphi \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("n_r_cloak", "b/(b-a)", "\u6597\u7bf7\u6298\u5c04\u7387\uff0cr \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("n_theta_cloak", "b/(b-a)*(r-a)/r", "\u6597\u7bf7\u6298\u5c04\u7387\uff0ctheta \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("n_phi_cloak", "n_theta_cloak", "\u6597\u7bf7\u6298\u5c04\u7387\uff0cphi \u5206\u91cf");
    model.component("comp1").variable("var1").set("cos_phi", "x/rh");
    model.component("comp1").variable("var1").set("sin_phi", "y/rh");
    model.component("comp1").variable("var1")
         .set("n_r", "n_air+(n_r_cloak-n_air)*flc2hs(b[1/m]-r[1/m],dr)", "\u6298\u5c04\u7387\uff0cr \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("n_theta", "n_air+(n_theta_cloak-n_air)*flc2hs(b[1/m]-r[1/m],dr)", "\u6298\u5c04\u7387\uff0ctheta \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("n_phi", "n_air+(n_phi_cloak-n_air)*flc2hs(b[1/m]-r[1/m],dr)", "\u6298\u5c04\u7387\uff0cphi \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("H_photon", "c_const*sqrt(pr^2/n_r^2+p_theta^2/n_theta^2+p_phi^2/n_phi^2+eps)", "\u8272\u6563\u5173\u7cfb");

    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "Hamiltonian", 0);
    model.component("comp1").physics("pt").feature("pp1").set("H", "H_photon");
    model.component("comp1").physics("pt").feature("pp1").set("mp", 1);
    model.component("comp1").physics("pt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg1").setIndex("x0", -1, 0);
    model.component("comp1").physics("pt").feature("relg1")
         .setIndex("x0", "range(-0.38,0.01,-0.02) range(0.02,0.01,0.38)", 1);
    model.component("comp1").physics("pt").feature("relg1").set("v0", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "hmax_cloak");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "hmax_cloak/2");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "hmax/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,(2[m]/c_const-0)/300,2[m]/c_const)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("odesolvertype", "explicit");
    model.sol("sol1").feature("t1").set("rkmethod", "dopri5");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 301, 0);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("linetype", "line");
    model.result("pg1").feature("traj1").set("pointtype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").set("expr", "cos_phi");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").create("sel1", "Selection");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.result("pg1").feature("surf1").feature("sel1").selection().set(6, 8, 14, 18);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("data", "dset1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().geom("geom1", 3);
    model.result("pg1").feature("surf2").feature("sel1").selection().set(3);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "part1");
    model.result().dataset("cpl1").set("quickx", -0.99);
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").set("quickx", 0.99);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u5bf9\u4e8e\u521d\u59cb\u4f4d\u7f6e\u7684\u5c04\u7ebf\u4f4d\u7f6e");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "y \u4f4d\u7f6e (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "z \u4f4d\u7f6e (m)");
    model.result("pg2").create("poma1", "PoincareMap");
    model.result("pg2").feature("poma1").set("data", "cpl1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("poma2", "poma1");
    model.result("pg2").run();
    model.result("pg2").feature("poma2").set("data", "cpl2");
    model.result("pg2").feature("poma2").set("color", "blue");
    model.result("pg2").feature("poma2").set("sphereradiusscaleactive", true);
    model.result("pg2").feature("poma2").set("sphereradiusscale", 0.7);
    model.result("pg2").feature("poma2").set("titletype", "none");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6a2a\u5411\u4f4d\u7f6e\u7684\u53d8\u5316");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u521d\u59cb\u4f4d\u7f6e (mm)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u4f4d\u7f6e\u53d8\u5316 (mm)");
    model.result("pg3").create("ptp1", "Particle1D");
    model.result("pg3").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptp1").set("linewidth", "preference");
    model.result("pg3").feature("ptp1").set("expr", "qy-at(0,qy)");
    model.result("pg3").feature("ptp1").set("unit", "mm");
    model.result("pg3").feature("ptp1").set("titletype", "none");
    model.result("pg3").feature("ptp1").set("xdata", "expr");
    model.result("pg3").feature("ptp1").set("xdataexpr", "at(0,qy)");
    model.result("pg3").feature("ptp1").set("xdataunit", "mm");
    model.result("pg3").feature("ptp1").set("linemarker", "point");
    model.result("pg3").run();

    model.title("\u7406\u60f3\u6597\u7bf7");

    model
         .description("\u672c\u4f8b\u8bbe\u8ba1\u4e86\u4e00\u4e2a\u5149\u5b66\u9690\u5f62\u88c5\u7f6e\uff0c\u5176\u4e2d\u4f7f\u7528\u5404\u5411\u5f02\u6027\u5a92\u4ecb\u4f7f\u4e00\u4e2a\u7269\u4f53\u4ea7\u751f\u9690\u5f62\u6548\u679c\uff0c\u5728\u89c2\u6d4b\u8005\u7684\u89c6\u89d2\u4e2d\u7535\u78c1\u6ce2\u4fdd\u6301\u4e0d\u53d8\u3002\u201c\u6570\u5b66\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u4e2d\u7684\u54c8\u5bc6\u987f\u516c\u5f0f\u7528\u4e8e\u8ffd\u8e2a\u5c04\u7ebf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ideal_cloak.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
