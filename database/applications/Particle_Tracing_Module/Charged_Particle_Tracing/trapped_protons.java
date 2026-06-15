/*
 * trapped_protons.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:52 by COMSOL 6.3.0.290. */
public class trapped_protons {

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

    model.param().set("Re", "6371.2[km]");
    model.param().descr("Re", "\u5730\u7403\u534a\u5f84");
    model.param().set("E0", "10[MeV]");
    model.param().descr("E0", "\u521d\u59cb\u7c92\u5b50\u80fd\u91cf");
    model.param().set("alpha", "30[deg]");
    model.param().descr("alpha", "\u8d64\u9053\u503e\u89d2");

    model.component("comp1").geom("geom1").lengthUnit("Mm");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "Re");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("r", "5*Re");
    model.component("comp1").geom("geom1").run("sph2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sph2");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("sph1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("dif1", 1, 2, 3, 4, 9, 10, 13, 16);

    model.component("comp1").selection().create("ball1", "Ball");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("ball1").set("entitydim", 2);
    model.component("comp1").selection("ball1").set("r", "Re");

    model.component("comp1").physics("cpt").prop("Formulation").setIndex("Formulation", "NewtonianFirstOrder", 0);
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").setIndex("RelativisticCorrection", 1, 0);
    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Proton");
    model.component("comp1").physics("cpt").create("mf1", "MagneticForce", 3);
    model.component("comp1").physics("cpt").feature("mf1").selection().all();
    model.component("comp1").physics("cpt").feature("mf1").set("B_src", "EarthsMagneticField");
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "2*Re", 0);
    model.component("comp1").physics("cpt").feature("relg1").set("VelocitySpecification", "SpecifyKineticEnergy");
    model.component("comp1").physics("cpt").feature("relg1").set("Ep0", "E0");
    model.component("comp1").physics("cpt").feature("relg1")
         .set("L0", new String[]{"0", "sin(alpha)", "cos(alpha)"});
    model.component("comp1").physics("cpt").create("aux1", "AuxiliaryField", -1);
    model.component("comp1").physics("cpt").feature("aux1").set("fieldVariableName", "Lm");
    model.component("comp1").physics("cpt").create("aux2", "AuxiliaryField", -1);
    model.component("comp1").physics("cpt").feature("aux2").set("fieldVariableName", "Ea");
    model.component("comp1").physics("cpt").create("vre1", "VelocityReinitialization", 3);
    model.component("comp1").physics("cpt").feature("vre1").selection().all();
    model.component("comp1").physics("cpt").feature("vre1")
         .set("ev", "(cpt.vx*cpt.mf1.Berx+cpt.vy*cpt.mf1.Bery+cpt.vz*cpt.mf1.Berz)<0 && Lm==0");
    model.component("comp1").physics("cpt").feature("vre1").set("EffectOnPrimaryParticle", "NoneEffect");
    model.component("comp1").physics("cpt").feature("vre1").set("caux_aux1", true);
    model.component("comp1").physics("cpt").feature("vre1")
         .set("daux_aux1", "(-acos(qz/sqrt(qx^2+qy^2+qz^2))+pi/2)*(180/pi)");
    model.component("comp1").physics("cpt").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("cpt").feature("relg2").set("L0", new String[]{"0", "sin(Ea)", "cos(Ea)"});
    model.component("comp1").physics("cpt").feature("relg2").set("DistributionFunction_aux2", "ListOfValues");
    model.component("comp1").physics("cpt").feature("relg2")
         .setIndex("vals_aux2", "range(10[deg],5[deg],80[deg])", 0);
    model.component("comp1").physics("cpt").feature("relg2").set("OrderPart_aux2", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time")
         .set("disabledphysics", new String[]{"cpt/aux1", "cpt/aux2", "cpt/vre1", "cpt/relg2"});
    model.study("std1").feature("time").set("tlist", "range(0,0.005,3)");
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
    model.result("pg1").setIndex("looplevel", 601, 0);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("linetype", "tube");
    model.result("pg1").feature("traj1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("traj1").set("tuberadiusscale", 0.01);
    model.result("pg1").feature("traj1").set("interpolation", "uniform");
    model.result("pg1").feature("traj1").set("interpcount", 2000);
    model.result("pg1").feature("traj1").set("pointtype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.mf1.normB");
    model.result("pg1").feature("traj1").feature("col1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("ball1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cpt.mf1.Berx", "cpt.mf1.Bery", "cpt.mf1.Berz"});
    model.result("pg2").feature("str1")
         .set("descr", "\u78c1\u901a\u5bc6\u5ea6\uff0c\u5730\u7403\uff08\u8f90\u72b6\uff09");
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("number", 50);
    model.result("pg2").feature("str1").set("startdata", "cpl1");
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("expr", "cpt.mf1.normB");
    model.result("pg2").feature("str1").feature("col1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg2").feature("str1").feature("col1").set("unit", "nT");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "dset2");
    model.result("pg2").feature("surf1").set("expr", "cpt.mf1.normB");
    model.result("pg2").feature("surf1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg2").feature("surf1").set("unit", "nT");
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u80fd\u91cf\u635f\u5931");
    model.result("pg3").set("data", "part1");
    model.result("pg3").create("ptp1", "Particle1D");
    model.result("pg3").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptp1").set("linewidth", "preference");
    model.result("pg3").feature("ptp1").set("expr", "1-(cpt.Ep)/at(0,cpt.Ep)");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"cpt/relg1"});
    model.study("std2").feature("time").set("tlist", "range(0,0.001,0.7)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol2");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_cpt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "cpt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part2");
    model.result("pg4").setIndex("looplevel", 701, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (cpt) 1");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("linetype", "line");
    model.result("pg4").feature("traj1").set("pointtype", "none");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "Ea*180/pi");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "dset2");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u955c\u50cf\u70b9\u7eac\u5ea6");
    model.result("pg5").set("data", "part2");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u8d64\u9053\u503e\u89d2 (deg)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u955c\u50cf\u70b9\u7eac\u5ea6 (deg)");
    model.result("pg5").create("ptp1", "Particle1D");
    model.result("pg5").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptp1").set("linewidth", "preference");
    model.result("pg5").feature("ptp1").set("expr", "Lm");
    model.result("pg5").feature("ptp1").set("descr", "\u8f85\u52a9\u56e0\u53d8\u91cf Lm");
    model.result("pg5").feature("ptp1").set("xdata", "expr");
    model.result("pg5").feature("ptp1").set("xdataexpr", "Ea*180/pi");
    model.result("pg5").run();

    model.title("\u88ab\u6355\u83b7\u8d28\u5b50\u5728\u5730\u7403\u78c1\u573a\u4e2d\u7684\u8fd0\u52a8");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u76f8\u5bf9\u8bba\u8d28\u5b50\u5728\u5730\u7403\u78c1\u573a\u4e2d\u7684\u8fd0\u52a8\u8f68\u8ff9\u3002\u88ab\u6355\u83b7\u8d28\u5b50\u7684\u955c\u50cf\u70b9\u7eac\u5ea6\u4f5c\u4e3a\u8d28\u5b50\u91ca\u653e\u7684\u8d64\u9053\u4fef\u4ef0\u89d2\u7684\u51fd\u6570\u8fdb\u884c\u8ba1\u7b97\u3002\u672c\u4f8b\u91c7\u7528\u201c\u78c1\u529b\u201d\u7279\u5f81\uff0c\u53ef\u4ee5\u901a\u8fc7\u5176\u4e2d\u7684\u5185\u7f6e\u9009\u9879\u6765\u4f7f\u7528\u201c\u56fd\u9645\u5730\u78c1\u53c2\u8003\u573a\u201d(IGRF) \u6570\u636e\u3002");

    model.label("trapped_protons.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
