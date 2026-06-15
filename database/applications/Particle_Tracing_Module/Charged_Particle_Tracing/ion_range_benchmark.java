/*
 * ion_range_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:50 by COMSOL 6.3.0.290. */
public class ion_range_benchmark {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E0", "1e4[MeV]", "\u8d28\u5b50\u521d\u59cb\u80fd\u91cf");
    model.param()
         .set("LE0", "log10(E0/1[MeV])", "\u521d\u59cb\u80fd\u91cf\uff08\u5355\u4f4d\uff1aMeV\uff09\u5bf9\u6570");
    model.param().set("mp", "mp_const", "\u8d28\u5b50\u8d28\u91cf");
    model.param().set("Zp", "1", "\u8d28\u5b50\u7535\u8377\u6570");
    model.param().set("rhoSi", "1.33[g/cm^3]", "\u7845\u8d28\u91cf\u5757\u5bc6\u5ea6");
    model.param()
         .set("Ltmax", "0.0003*LE0^6+0.0007*LE0^5-0.0163*LE0^4-0.013*LE0^3+0.2294*LE0^2+0.8055*LE0-11.304", "\u4eff\u771f\u65f6\u95f4\u5bf9\u6570");
    model.param()
         .set("LL", "0.0035*LE0^5-0.0161*LE0^4-0.0725*LE0^3+0.2558*LE0^2+1.5964*LE0-4.3234", "\u8fb9\u754c\u5927\u5c0f\u5bf9\u6570");
    model.param().set("tmax", "10^Ltmax[s]", "\u4eff\u771f\u65f6\u95f4");
    model.param().set("L", "10^LL[m]", "\u8fb9\u6846\u5927\u5c0f");
    model.param().set("tsize", "tmax/1e3", "\u6700\u5927\u65f6\u95f4\u6b65\u957f");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "ion_range_benchmark_ranges.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").setIndex("argunit", "MeV", 0);
    model.component("comp1").func("int1").setEntry("columnType", "col2", "value");
    model.component("comp1").func("int1").setEntry("funcnames", "col2", "CSDA");
    model.component("comp1").func("int1").setIndex("fununit", "g/cm^2", 0);
    model.component("comp1").func("int1").setEntry("columnType", "col3", "none");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "ion_range_benchmark_ranges.txt");
    model.component("comp1").func("int2").setIndex("argunit", "MeV", 0);
    model.component("comp1").func("int2").setEntry("columnType", "col2", "none");
    model.component("comp1").func("int2").setEntry("funcnames", "col3", "Proj");
    model.component("comp1").func("int2").setIndex("fununit", "g/cm^2", 0);
    model.component("comp1").func("int2").importData();

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "L", "L"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cpt").prop("RelativisticCorrection").setIndex("RelativisticCorrection", 1, 0);
    model.component("comp1").physics("cpt").create("pmi1", "ParticleMatterInteractions", 3);
    model.component("comp1").physics("cpt").feature("pmi1").selection().all();
    model.component("comp1").physics("cpt").feature("pmi1").create("il1", "IonizationLoss", 3);
    model.component("comp1").physics("cpt").feature("pmi1").create("ns1", "NuclearStopping", 3);
    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Proton");
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "-L/4", 0);
    model.component("comp1").physics("cpt").feature("relg1")
         .setIndex("x0", "range(-L/1e3,(L/1e3-(-L/1e3))/1000,L/1e3)", 1);
    model.component("comp1").physics("cpt").feature("relg1").set("VelocitySpecification", "SpecifyKineticEnergy");
    model.component("comp1").physics("cpt").feature("relg1").set("Ep0", "E0");
    model.component("comp1").physics("cpt").create("aux1", "AuxiliaryField", -1);
    model.component("comp1").physics("cpt").feature("aux1").set("R", 1);
    model.component("comp1").physics("cpt").feature("aux1").set("Integrate", "AlongParticleTrajectory");
    model.component("comp1").physics("cpt").feature("aux1").set("DependentVariableQuantity", "length");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhoSi"});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "E0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "J", 0);
    model.study("std1").feature("param").setIndex("pname", "E0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "J", 0);
    model.study("std1").feature("param").setIndex("punit", "MeV", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^{range(-3,1,2)}", 0);
    model.study("std1").feature("time").set("tlist", "range(0,1/20,1)*tmax");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "tmax/1e3");

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
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").setIndex("looplevel", 6, 1);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").set("looplevel", new int[]{21, 3});
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("linetype", "line");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("colortable", "Cividis");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u505c\u6b62\u8ddd\u79bb");
    model.result("pg2").set("data", "part1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("ptp1", "Particle1D");
    model.result("pg2").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptp1").set("linewidth", "preference");
    model.result("pg2").feature("ptp1").set("expr", "cpt.ave(rp)");
    model.result("pg2").feature("ptp1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("ptp1").set("xdata", "expr");
    model.result("pg2").feature("ptp1").set("xdataexpr", "E0");
    model.result("pg2").feature("ptp1").set("xdataunit", "MeV");
    model.result("pg2").feature("ptp1").set("linestyle", "none");
    model.result("pg2").feature("ptp1").set("linecolor", "red");
    model.result("pg2").feature("ptp1").set("linewidth", 2);
    model.result("pg2").feature("ptp1").set("linemarker", "star");
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "CSDA(E0)/rhoSi", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "CSDA", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "Proj(E0)/rhoSi", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u6295\u5f71", 1);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "E0");
    model.result("pg2").feature("glob1").set("xdataunit", "MeV");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linecolor", "black");
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("xlog", true);
    model.result("pg2").run();

    model.title("\u79bb\u5b50\u5c04\u7a0b\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u57fa\u51c6\u6a21\u578b\u6a21\u62df\u9ad8\u80fd\u8d28\u5b50\u901a\u8fc7\u7845\u65f6\u7684\u7535\u79bb\u635f\u8017\u4e0e\u6838\u6563\u5c04\uff0c\u5176\u4e2d\u4f7f\u7528\u53c2\u6570\u5316\u626b\u63cf\u4f7f\u8d28\u5b50\u7684\u521d\u59cb\u80fd\u91cf\u5728 1\u00a0keV \u5230 100\u00a0MeV \u4e4b\u95f4\u53d1\u751f\u53d8\u5316\u3002\n\n\u672c\u4f8b\u5c06\u8d28\u5b50\u7684\u5e73\u5747\u8def\u5f84\u957f\u5ea6\u4e0e\u8fde\u7eed\u51cf\u901f\u8fd1\u4f3c (CSDA) \u4e0b\u7684\u79bb\u5b50\u5c04\u7a0b\u548c\u521d\u59cb\u8fd0\u52a8\u65b9\u5411\u7684\u6295\u5f71\u5c04\u7a0b\u7684\u516c\u5e03\u503c\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u8868\u660e\uff0c\u4eff\u771f\u6570\u636e\u4e0e\u5b9e\u9a8c\u6570\u636e\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("ion_range_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
