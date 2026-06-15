/*
 * einzel_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:49 by COMSOL 6.3.0.290. */
public class einzel_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("V0", "-10[kV]", "\u5185\u5706\u67f1\u4e0a\u7684\u7535\u538b");
    model.param().set("E0", "20[keV]", "\u7c92\u5b50\u52a8\u80fd");
    model.param().set("vx0", "sqrt(2*E0/me_const)", "\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param()
         .set("T", "L_vac/vx0", "\u65e0\u5916\u529b\u4f5c\u7528\u7684\u7c92\u5b50\u5230\u8fbe\u7ed3\u675f\u90e8\u5206\u6240\u7528\u7684\u65f6\u95f4");

    model.component("comp1").geom("geom1").insertFile("einzel_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").selection("sel1").set(2, 4);
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(2, 4);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7535\u6781\u8fb9\u754c");
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6240\u6709\u67f1\u9762");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").physics("es").selection().set(1);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").label("\u63a5\u5730\u7684\u771f\u7a7a\u5ba4\u58c1");
    model.component("comp1").physics("es").feature("gnd1").selection().set(3, 4, 8, 13);
    model.component("comp1").physics("es").create("gnd2", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd2").label("\u63a5\u5730\u5706\u67f1");
    model.component("comp1").physics("es").feature("gnd2").selection().named("sel1");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().named("sel2");
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("\u622a\u9762\uff1ay=0");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7b49\u52bf\u9762");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("data", "cpl1");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("color", "black");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").set("number", 20);
    model.result("pg1").feature("iso1").set("colortable", "Viridis");
    model.result("pg1").feature("iso1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("iso1").feature("filt1").set("expr", "y>0");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u8fb9\u7f18\u573a");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("colortable", "Viridis");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("planecoordsys", "cartesian");
    model.result("pg2").feature("arws1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg2").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg2").feature("arws1").set("xcoord", "range(0.42,0.005,0.6)");
    model.result("pg2").feature("arws1").set("arrowymethod", "coord");
    model.result("pg2").feature("arws1").set("ycoord", "range(-0.095,0.005,0.095)");
    model.result("pg2").feature("arws1").set("color", "white");

    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/cpt", false);

    model.component("comp1").physics("cpt").selection().set(1);
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").setIndex("RelativisticCorrection", 1, 0);
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(5, 6, 9, 10);
    model.component("comp1").physics("cpt").feature("pbeam1").set("e1rms", "5[um]");
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "E0");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,T/20,T*1.05)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 22, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 22, 0);
    model.result("pg4").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt)");
    model.result("pg4").create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").set("plotdata", "global");
    model.result("pg4").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg4").feature("pttraj1").create("col1", "Color");
    model.result("pg4").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "line");
    model.result("pg3").feature("traj1").set("pointtype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.Ep/E0");
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "Prism");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("pttraj1").set("linetype", "tube");
    model.result("pg4").run();
    model.result("pg4").feature("pttraj1").feature("col1").set("colortable", "Prism");
    model.result("pg4").run();

    model.title("\u5355\u900f\u955c");

    model
         .description("\u5355\u900f\u955c\u662f\u7528\u4e8e\u805a\u7126\u5e26\u7535\u7c92\u5b50\u675f\u7684\u9759\u7535\u88c5\u7f6e\uff0c\u53ef\u7528\u4e8e\u9634\u6781\u5c04\u7ebf\u7ba1\u3001\u79bb\u5b50\u675f\u548c\u7535\u5b50\u675f\u8bd5\u9a8c\u4ee5\u53ca\u79bb\u5b50\u63a8\u8fdb\u7cfb\u7edf\u3002\n\n\u8fd9\u4e2a\u7279\u5b9a\u7684\u6a21\u578b\u7531\u4e09\u4e2a\u8f74\u5411\u5bf9\u9f50\u7684\u5706\u67f1\u7ec4\u6210\u3002\u5916\u4fa7\u7684\u4e24\u4e2a\u5706\u67f1\u63a5\u5730\uff0c\u4e2d\u95f4\u7684\u5706\u67f1\u63a5\u6709\u56fa\u5b9a\u7535\u538b\u3002\u4f7f\u7528\u9759\u7535 \u63a5\u53e3\u8ba1\u7b97\u4e86\u4e09\u7ef4\u9759\u7535\u573a\uff0c\u4f7f\u7528\u5e26\u7535\u7c92\u5b50\u8ffd\u8e2a \u63a5\u53e3\u8ba1\u7b97\u4e86\u7c92\u5b50\u8f68\u8ff9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("einzel_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
