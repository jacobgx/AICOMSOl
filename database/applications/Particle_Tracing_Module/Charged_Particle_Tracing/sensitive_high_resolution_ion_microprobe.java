/*
 * sensitive_high_resolution_ion_microprobe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:52 by COMSOL 6.3.0.290. */
public class sensitive_high_resolution_ion_microprobe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cpt", true);

    model.component("comp1").geom("geom1")
         .insertFile("sensitive_high_resolution_ion_microprobe_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("r", "sqrt((x-xc)^2+(y-yc)^2)", "\u4e0e\u7535\u5206\u6790\u4eea\u5f2f\u66f2\u4e2d\u5fc3\u7684\u5f84\u5411\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("xc", "-r0_analyzer", "\u7535\u5206\u6790\u4eea\u5f2f\u66f2\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("yc", "hsample+hentrance+h_analyzer_pre", "\u7535\u5206\u6790\u4eea\u5f2f\u66f2\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("theta", "atan2(y-yc,x-xc)", "\u7535\u5206\u6790\u4eea\u5f2f\u89d2");
    model.component("comp1").variable("var1")
         .set("Ex", "E0*cos(theta)", "\u7535\u5206\u6790\u4eea\u7535\u573a\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Ey", "E0*sin(theta)", "\u7535\u5206\u6790\u4eea\u7535\u573a\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Ez", "0[V/m]", "\u7535\u5206\u6790\u4eea\u7535\u573a\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Bx", "0[T]", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u78c1\u901a\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("By", "0[T]", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u78c1\u901a\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Bz", "B0", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ei0", "50[eV]", "\u6700\u53ef\u80fd\u7684\u521d\u59cb\u80fd\u91cf");
    model.param().set("E0", "-2*Ei0/(e_const*r0_analyzer)", "\u7535\u5206\u6790\u4eea\u7684\u7535\u573a\u6a21");
    model.param()
         .set("V", "sqrt(2*Ei0/(Mr*mp_const))", "\u6700\u53ef\u80fd\u7684\u521d\u59cb\u80fd\u91cf\u4e0b\u7684\u901f\u5ea6\u6a21");
    model.param()
         .set("B0", "-Mr*mp_const*V/e_const/r_magnet", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.param().set("Mr", "146", "\u539f\u5b50\u91cf");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9759\u7535\u5206\u6790\u5668");
    model.component("comp1").selection("sel1").set(6);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6247\u5f62\u78c1\u573a");
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6837\u54c1\u5ba4");
    model.component("comp1").selection("sel3").set(8);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u68c0\u6d4b\u5668");
    model.component("comp1").selection("sel4").set(1);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u8fc7\u6ee4\u5668");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5")
         .set(5, 6, 7, 8, 14, 15, 16, 17, 18, 19, 20, 39, 40, 41, 42, 43, 44, 45, 46, 47, 51, 52, 63, 64, 65, 66, 67, 68, 69, 72, 75, 76, 77, 78, 79, 80, 83, 84, 85, 86, 91, 92, 93, 94);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5b54\u5f84");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(23, 28, 52, 80);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .setIndex("ParticleReleaseSpecification", "SpecifyCurrent", 0);
    model.component("comp1").physics("cpt").prop("Formulation").setIndex("Formulation", "NewtonianFirstOrder", 0);
    model.component("comp1").physics("cpt").prop("StoreParticleStatusData")
         .setIndex("StoreParticleStatusData", 1, 0);
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "Mr*mp_const");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().named("sel1");
    model.component("comp1").physics("cpt").feature("ef1").set("E", new String[]{"Ex", "Ey", "Ez"});
    model.component("comp1").physics("cpt").create("mf1", "MagneticForce", 3);
    model.component("comp1").physics("cpt").feature("mf1").selection().named("sel2");
    model.component("comp1").physics("cpt").feature("mf1").set("B", new String[]{"Bx", "By", "Bz"});
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(74);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("N", 5000, 0);
    model.component("comp1").physics("cpt").feature("pbeam1")
         .set("TransverseVelocityDistributionSpecification", "SpecifyPhaseSpaceEllipseDimensions");
    model.component("comp1").physics("cpt").feature("pbeam1").set("xm", "1[cm]");
    model.component("comp1").physics("cpt").feature("pbeam1").set("xmp", 0.001);
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "Ei0");
    model.component("comp1").physics("cpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt1").selection().set(11);
    model.component("comp1").physics("cpt").feature("pcnt1").set("ReleaseFeature", "pbeam1");
    model.component("comp1").physics("cpt").create("pcnt2", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt2").selection().set(65);
    model.component("comp1").physics("cpt").feature("pcnt2").set("ReleaseFeature", "pbeam1");
    model.component("comp1").physics("cpt").create("pcnt3", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt3").selection().set(68);
    model.component("comp1").physics("cpt").feature("pcnt3").set("ReleaseFeature", "pbeam1");
    model.component("comp1").physics("cpt").create("pcnt4", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt4").selection().set(51);
    model.component("comp1").physics("cpt").feature("pcnt4").set("ReleaseFeature", "pbeam1");
    model.component("comp1").physics("cpt").create("pcnt5", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt5").selection().set(8);
    model.component("comp1").physics("cpt").feature("pcnt5").set("ReleaseFeature", "pbeam1");
    model.component("comp1").physics("cpt").create("wall2", "Wall", 2);
    model.component("comp1").physics("cpt").feature("wall2").selection().named("sel6");
    model.component("comp1").physics("cpt").feature("wall2").set("WallCondition", "Pass");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3, 11, 61, 74);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature().duplicate("ftri2", "ftri1");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,1)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "part1");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt)");
    model.result("pg2").create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").set("plotdata", "global");
    model.result("pg2").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg2").feature("pttraj1").create("col1", "Color");
    model.result("pg2").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("linetype", "line");
    model.result("pg1").feature("traj1").set("pointtype", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.Ep");
    model.result("pg1").feature("traj1").feature("col1").set("descr", "\u7c92\u5b50\u52a8\u80fd");
    model.result("pg1").feature("traj1").feature("col1").set("unit", "eV");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("pttraj1")
         .set("expr", new String[]{"cpt.pcnt1.qavtx", "cpt.pcnt1.qavty", "cpt.pcnt1.qavtz"});
    model.result("pg2").run();
    model.result("pg2").feature("pttraj1").feature("col1").active(false);
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").set("expr", new String[]{"cpt.pcnt1.alpha"});
    model.result().numerical("gev1").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();

    model.title("\u654f\u611f\u6027\u9ad8\u5206\u8fa8\u7387\u79bb\u5b50\u5fae\u63a2\u9488");

    model
         .description("\u654f\u611f\u6027\u9ad8\u5206\u8fa8\u7387\u79bb\u5b50\u5fae\u63a2\u9488 (SHRIMP) \u901a\u8fc7\u4f7f\u5165\u5c04\u79bb\u5b50\u675f\u53d7\u5230\u9002\u5f53\u8c03\u8c10\u7684\u7535\u78c1\u529b\u4f5c\u7528\uff0c\u4f20\u8f93\u7ed9\u5b9a\u521d\u59cb\u80fd\u91cf\u548c\u7279\u5b9a\u8377\u8d28\u6bd4\u7684\u79bb\u5b50\u3002\u79bb\u5b50\u675f\u9996\u5148\u901a\u8fc7\u5f84\u5411\u7535\u529b\u7684\u66f2\u8fb9\u6247\u533a\uff0c\u7136\u540e\u901a\u8fc7\u7b2c\u4e8c\u4e2a\u5177\u6709\u5747\u5300\u78c1\u901a\u5bc6\u5ea6\u7684\u66f2\u8fb9\u6247\u533a\u3002\n\n\u672c\u6559\u7a0b\u5229\u7528\u201c\u5e26\u7535\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u7684\u201c\u7c92\u5b50\u675f\u201d\u7279\u5f81\u6765\u7814\u7a76\u9ad8\u7cbe\u5ea6\u5149\u8c31\u4eea\u7684\u6027\u80fd\uff0c\u5176\u4e2d\u53ea\u6709\u4e00\u5c0f\u90e8\u5206\u5165\u5c04\u79bb\u5b50\u675f\u53ef\u4ee5\u4f20\u9001\u5230\u68c0\u6d4b\u5668\u3002\u6b64\u6a21\u578b\u8ba1\u7b97\u4e86\u4f20\u8f93\u6982\u7387\uff0c\u5e76\u5c06\u4f20\u8f93\u79bb\u5b50\u675f\u7684\u6807\u79f0\u8f68\u8ff9\u53ef\u89c6\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sensitive_high_resolution_ion_microprobe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
