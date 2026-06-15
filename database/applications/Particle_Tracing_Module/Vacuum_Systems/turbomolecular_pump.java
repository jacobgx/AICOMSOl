/*
 * turbomolecular_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:55 by COMSOL 6.3.0.290. */
public class turbomolecular_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Vacuum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pt", true);

    model.component("comp1").geom("geom1").insertFile("turbomolecular_pump_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").sorder("linear");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("T0", "300[K]", "\u6e29\u5ea6");
    model.param("par2").set("M", "39.948[g/mol]", "\u6c29\u7684\u6469\u5c14\u8d28\u91cf");
    model.param("par2").set("m", "M/N_A_const", "\u6c29\u8d28\u91cf");
    model.param("par2")
         .set("vmp", "sqrt(2*k_B_const*T0/m)", "\u6700\u53ef\u80fd\u7684\u901f\u5ea6\uff08\u9ea6\u514b\u65af\u97e6\u5206\u5e03\uff09");
    model.param("par2").set("C", "0.5", "\u5747\u65b9\u6839\u534a\u5f84\u7684\u53f6\u7247\u901f\u5ea6\u6bd4");
    model.param("par2").set("vb", "C*vmp", "\u5747\u65b9\u6839\u534a\u5f84\u7684\u53f6\u7247\u901f\u5ea6");
    model.param("par2").set("omega", "vb/rrms[rad]", "\u5355\u7ea7\u89d2\u901f\u5ea6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("M12", "pt.pcnt1.alpha");
    model.component("comp1").variable("var1").descr("M12", "\u4f20\u8f93\u6982\u7387\uff0c\u6b63\u5411");
    model.component("comp1").variable("var1").set("M21", "pt.pcnt2.alpha");
    model.component("comp1").variable("var1").descr("M21", "\u4f20\u8f93\u6982\u7387\uff0c\u53cd\u5411");
    model.component("comp1").variable("var1").set("Kmax", "M12/M21");
    model.component("comp1").variable("var1").descr("Kmax", "\u6700\u5927\u538b\u7f29\u6bd4");
    model.component("comp1").variable("var1").set("Wmax", "M12-M21");
    model.component("comp1").variable("var1").descr("Wmax", "\u6700\u5927\u901f\u5ea6\u56e0\u5b50");

    model.component("comp1").physics("pt").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("pt").feature("pp1").set("mp", "m");
    model.component("comp1").physics("pt").create("rf1", "RotatingFrame", 3);
    model.component("comp1").physics("pt").feature("rf1").set("normOmega", "omega");
    model.component("comp1").physics("pt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("pt").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("pt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("pt").feature("inl1").setIndex("N", 10000, 0);
    model.component("comp1").physics("pt").feature("inl1").set("VelocitySpecification", "Thermal");
    model.component("comp1").physics("pt").feature("inl1").set("T", "T0");
    model.component("comp1").physics("pt").feature("inl1").set("SubtractMovingFrameVelocity", true);
    model.component("comp1").physics("pt").create("inl2", "Inlet", 2);
    model.component("comp1").physics("pt").feature("inl2").selection().named("geom1_sel2");
    model.component("comp1").physics("pt").feature("inl2").set("InitialPosition", "Density");
    model.component("comp1").physics("pt").feature("inl2").setIndex("N", 10000, 0);
    model.component("comp1").physics("pt").feature("inl2").set("VelocitySpecification", "Thermal");
    model.component("comp1").physics("pt").feature("inl2").set("T", "T0");
    model.component("comp1").physics("pt").feature("inl2").set("SubtractMovingFrameVelocity", true);
    model.component("comp1").physics("pt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("pt").feature("pcnt1")
         .label("\u7c92\u5b50\u8ba1\u6570\u5668\uff08\u5165\u53e3 1 \u4f20\u8f93\uff09");
    model.component("comp1").physics("pt").feature("pcnt1").selection().named("geom1_sel2");
    model.component("comp1").physics("pt").feature("pcnt1").set("ReleaseFeature", "inl1");
    model.component("comp1").physics("pt").create("pcnt2", "ParticleCounter", 2);
    model.component("comp1").physics("pt").feature("pcnt2")
         .label("\u7c92\u5b50\u8ba1\u6570\u5668\uff08\u5165\u53e3 2 \u4f20\u8f93\uff09");
    model.component("comp1").physics("pt").feature("pcnt2").selection().named("geom1_sel1");
    model.component("comp1").physics("pt").feature("pcnt2").set("ReleaseFeature", "inl2");
    model.component("comp1").physics("pt").create("tre1", "ThermalReEmission", 2);
    model.component("comp1").physics("pt").feature("tre1").label("\u6839\u90e8\u548c\u53f6\u7247\u8868\u9762");
    model.component("comp1").physics("pt").feature("tre1").selection().named("geom1_unisel1");
    model.component("comp1").physics("pt").feature("tre1").set("T", "T0");
    model.component("comp1").physics("pt").create("tre2", "ThermalReEmission", 2);
    model.component("comp1").physics("pt").feature("tre2").label("\u5c16\u7aef\u58c1");
    model.component("comp1").physics("pt").feature("tre2").selection().named("geom1_sel4");
    model.component("comp1").physics("pt").feature("tre2").set("T", "T0");
    model.component("comp1").physics("pt").feature("tre2").set("SubtractMovingFrameVelocityReflected", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "C", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,0.5,4)", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.5)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").setIndex("looplevel", 9, 1);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").setIndex("looplevel", 9, 1);
    model.result("pg2").label("\u5750\u6807\u7cfb\u901f\u5ea6 (pt)");
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"pt.rf1.vfx", "pt.rf1.vfy", "pt.rf1.vfz"});
    model.result("pg2").feature("arwv1").create("col1", "Color");
    model.result("pg2").feature("arwv1").feature("col1").set("expr", "pt.rf1.Vf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("expr", "pt.prf");
    model.result("pg1").feature("traj1").feature("col1").set("descr", "\u7c92\u5b50\u91ca\u653e\u7279\u5f81");
    model.result("pg1").feature("traj1").feature("col1").set("colortable", "TrafficLight");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 1);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("M12");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "M12", 0);
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u4ece\u5165\u53e3\u4f20\u8f93\u5230\u51fa\u53e3\u7684\u9897\u7c92\u5206\u6570", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob1").set("linecolor", "black");
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("M21");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "M21", 0);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u4ece\u51fa\u53e3\u4f20\u8f93\u5230\u5165\u53e3\u7684\u9897\u7c92\u5206\u6570", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("Kmax");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "Kmax", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6700\u5927\u538b\u7f29\u6bd4", 0);
    model.result("pg5").run();
    model.result("pg5").set("ylog", true);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("Wmax");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "Wmax", 0);
    model.result("pg6").run();
    model.result("pg6").set("ylog", false);

    model.title("\u6da1\u8f6e\u5206\u5b50\u6cf5");

    model
         .description("\u201c\u5206\u5b50\u6d41\u6a21\u5757\u201d\u4e2d\u7684\u81ea\u7531\u5206\u5b50\u6d41 \u63a5\u53e3\u662f\u4e00\u79cd\u6709\u6548\u5de5\u5177\uff0c\u7528\u4e8e\u5728\u6c14\u4f53\u5206\u5b50\u79fb\u52a8\u901f\u5ea6\u6bd4\u57df\u4e2d\u4efb\u4f55\u51e0\u4f55\u5b9e\u4f53\u79fb\u52a8\u901f\u5ea6\u5feb\u5f97\u591a\u65f6\u5bf9\u6781\u7a00\u8584\u6c14\u4f53\u8fdb\u884c\u5efa\u6a21\u3002\u5bf9\u4e8e\u6da1\u8f6e\u5206\u5b50\u6cf5\uff0c\u53f6\u7247\u7684\u8fd0\u52a8\u901f\u5ea6\u4e0e\u6c14\u4f53\u5206\u5b50\u7684\u70ed\u901f\u5ea6\u76f8\u5f53\uff0c\u8fd9\u65f6\u9700\u8981\u4f7f\u7528\u8499\u7279\u5361\u7f57\u65b9\u6cd5\u3002\n\n\u672c\u4f8b\u8ba1\u7b97\u6da1\u8f6e\u5206\u5b50\u6cf5\u7684\u4e24\u4e2a\u65cb\u8f6c\u53f6\u7247\u95f4\u7684\u7a7a\u9699\u5185\u6c14\u4f53\u5206\u5b50\u7684\u8f68\u8ff9\uff0c\u6a21\u578b\u91c7\u7528\u65cb\u8f6c\u5750\u6807\u7cfb \u7279\u5f81\u5bf9\u9897\u7c92\u65bd\u52a0\u79bb\u5fc3\u529b\u548c\u79d1\u91cc\u5965\u5229\u529b\uff0c\u8ba1\u7b97\u4e86\u65cb\u8f6c\u53f6\u7247\u6240\u5728\u7684\u975e\u60ef\u6027\u53c2\u8003\u7cfb\u4e2d\u7684\u9897\u7c92\u8f68\u8ff9\u3002\u901a\u8fc7\u53c2\u6570\u5316\u626b\u63cf\u663e\u793a\u53f6\u7247\u901f\u5ea6\u5bf9\u538b\u7f29\u56e0\u5b50\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();

    model.label("turbomolecular_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
