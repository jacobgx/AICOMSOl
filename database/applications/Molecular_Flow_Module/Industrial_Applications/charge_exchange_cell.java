/*
 * charge_exchange_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:17 by COMSOL 6.3.0.290. */
public class charge_exchange_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("M_gas", "40[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff1a\u60f0\u6027\u6c14\u4f53 (Ar)");
    model.param().set("M_p", "1[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff1a\u7c92\u5b50 (H+)");
    model.param().set("E_p", "1[keV]", "\u80fd\u91cf\uff1a\u7c92\u5b50 (H+)");
    model.param().set("N0", "1000", "\u7c92\u5b50\u6570");
    model.param().set("E1", "2.16[eV]", "\u78b0\u649e 1 \u7684\u80fd\u91cf\u635f\u5931");
    model.param().set("E2", "13.6[eV]", "\u78b0\u649e 2 \u7684\u80fd\u91cf\u635f\u5931");
    model.param().set("E3", "15.6[eV]", "\u78b0\u649e 3 \u7684\u80fd\u91cf\u635f\u5931");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("Ar+H+=>H+Ar+");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "charge_exchange_cell_Qex1.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "Qex1");
    model.component("comp1").func("int1").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int1").setIndex("fununit", "m^2", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("H+Ar=>Ar+H+");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "charge_exchange_cell_Qex2.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "Qex2");
    model.component("comp1").func("int2").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int2").setIndex("fununit", "m^2", 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").label("H+Ar=>H+Ar+");
    model.component("comp1").func("int3").set("source", "file");
    model.component("comp1").func("int3").set("filename", "charge_exchange_cell_Qex3.txt");
    model.component("comp1").func("int3").importData();
    model.component("comp1").func("int3").set("funcname", "Qex3");
    model.component("comp1").func("int3").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int3").setIndex("fununit", "m^2", 0);

    model.component("comp1").geom("geom1").insertFile("charge_exchange_cell_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "M_gas", 0);
    model.component("comp1").physics("fmf").create("wall2", "Wall", 2);
    model.component("comp1").physics("fmf").feature("wall2").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall2").set("BoundaryCondition", "NumberOfSCCM");
    model.component("comp1").physics("fmf").feature("wall2").setIndex("sccmmfr", 0.05, 0);
    model.component("comp1").physics("fmf").feature("wall2").set("StandardFlowRateDefinedBy", "StandardP");
    model.component("comp1").physics("fmf").feature("wall2").selection().set(35, 36, 69, 71);
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(6);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("pspeed", "63[l/s]", 0);
    model.component("comp1").physics("fmf").create("ndr1", "NumberDensityReconDomain", 3);
    model.component("comp1").physics("fmf").feature("ndr1").selection().set(1);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").set("V0", "200[V]");
    model.component("comp1").physics("es").feature("pot1").selection().set(41, 42, 43, 44, 46, 90);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89);
    model.component("comp1").physics("cpt").prop("MaximumSecondary").setIndex("MaximumSecondary", 500, 0);
    model.component("comp1").physics("cpt").feature("pp1").label("H+");
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "M_p/N_A_const");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("pp2", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("cpt").feature("pp2").set("mp", "M_p/N_A_const");
    model.component("comp1").physics("cpt").feature("pp2").set("Z", 0);
    model.component("comp1").physics("cpt").feature("pp2").label("H");
    model.component("comp1").physics("cpt").create("pp3", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("cpt").feature("pp3").label("Ar+");
    model.component("comp1").physics("cpt").feature("pp3").set("mp", "M_gas/N_A_const");
    model.component("comp1").physics("cpt").feature("pp3").set("Z", 1);
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(47);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("N", "N0", 0);
    model.component("comp1").physics("cpt").feature("pbeam1").set("e1rms", "0.1[um]");
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "1[keV]");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().set(1);
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").create("col1", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col1").label("Ar+H+=>H+Ar+");
    model.component("comp1").physics("cpt").feature("col1").selection().set(1);
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "fmf.n_G");
    model.component("comp1").physics("cpt").feature("col1").set("ParticlesToAffect", "SingleSpecies");
    model.component("comp1").physics("cpt").feature("col1").create("ncex1", "NonResonantChargeExchange", 3);
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("xsec", "Qex1(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("dE", "E1");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1")
         .set("SpeciesToRelease", "IonAndNeutral");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("ReleasedIonProperties", "pp3");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("ReleasedNeutralProperties", "pp2");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("CountCollisions", true);
    model.component("comp1").physics("cpt").feature().duplicate("col2", "col1");
    model.component("comp1").physics("cpt").feature("col2").label("H+Ar=>Ar+H+");
    model.component("comp1").physics("cpt").feature("col2").set("AffectedParticleProperties", "pp2");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("xsec", "Qex2(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("dE", "E2");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("SpeciesToRelease", "Ion");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("ReleasedIonProperties", "pp1");
    model.component("comp1").physics("cpt").feature().duplicate("col3", "col2");
    model.component("comp1").physics("cpt").feature("col3").label("H+Ar=>H+Ar+");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("xsec", "Qex3(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("dE", "E3");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("ReleasedIonProperties", "pp3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Nc1", "cpt.sum(cpt.col1.ncex1.Nc)");
    model.component("comp1").variable("var1").descr("Nc1", "\u78b0\u649e\u6570\uff0c\u7c7b\u578b 1");
    model.component("comp1").variable("var1").set("Nc2", "cpt.sum(cpt.col2.ncex1.Nc)");
    model.component("comp1").variable("var1").descr("Nc2", "\u78b0\u649e\u6570\uff0c\u7c7b\u578b 2");
    model.component("comp1").variable("var1").set("Nc3", "cpt.sum(cpt.col3.ncex1.Nc)");
    model.component("comp1").variable("var1").descr("Nc3", "\u78b0\u649e\u6570\uff0c\u7c7b\u578b 3");
    model.component("comp1").variable("var1").set("Nctot", "Nc1+Nc2+Nc3");
    model.component("comp1").variable("var1").descr("Nctot", "\u78b0\u649e\u603b\u6570");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("xmin", 0);
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("condition", "allvertices");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size3").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cpt", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("scale", 1);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").label("\u7814\u7a76 1/\u89e3 1 \u6846\u9009\u62e9");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("box1");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -100, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 100, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u52bf");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("expr", "V");
    model.result("pg1").feature("slc1").set("colortable", "AuroraAustralis");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u538b\u529b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6c14\u4f53\u6570\u5bc6\u5ea6");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "fmf.ntot");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/fmf", false);
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("tunit", "\u00b5s");
    model.study("std2").feature("time").set("tlist", "range(0,0.01,0.5)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol2").feature("t1").set("maxstepgenalpha", "10[ns]");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt)");
    model.result("pg5").create("pttraj1", "PointTrajectories");
    model.result("pg5").feature("pttraj1").set("plotdata", "global");
    model.result("pg5").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg5").feature("pttraj1").create("col1", "Color");
    model.result("pg5").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("linetype", "line");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "cpt.Z");
    model.result("pg4").feature("traj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg4").run();
    model.result().dataset().duplicate("part2", "part1");
    model.result().dataset("part2").selection().geom("geom1", 2);
    model.result().dataset("part2").selection().set(8);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u6548\u7387");
    model.result().numerical("gev1").set("data", "part2");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "cpt.Nsel/N0*100", 0);
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("Nc1/Nctot");
    model.result().numerical("gev2").setIndex("expr", "Nc1/Nctot*100", 0);
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").label("Nc2/Nctot");
    model.result().numerical("gev3").setIndex("expr", "Nc2/Nctot*100", 0);
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").label("Nc3/Nctot");
    model.result().numerical("gev4").setIndex("expr", "Nc3/Nctot*100", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u6548\u7387");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Nc1/Nctot");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Nc2/Nctot");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Nc3/Nctot");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();

    model.title("\u901a\u8fc7\u7535\u8377\u4ea4\u6362\u5ba4\u4e2d\u548c\u8d28\u5b50\u675f");

    model
         .description("\u6c14\u5ba4\u5728\u79d1\u5b66\u4eea\u5668\u8bbe\u8ba1\u4e2d\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\uff0c\u53ef\u4ee5\u7528\u6765\u5728\u4eea\u5668\u7684\u4e3b\u771f\u7a7a\u7cfb\u7edf\u4e2d\u5b9a\u4e49\u4e00\u4e2a\u9ad8\u538b\u533a\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u4e00\u4e2a\u957f 100\u00a0mm\u3001\u5de5\u4f5c\u538b\u529b\u4e3a 1e-3\u00a0Torr \u7684\u9ad8\u538b\u533a\u88ab\u653e\u7f6e\u5728\u4e00\u4e2a\u5de5\u4f5c\u538b\u529b\u4e3a 1e-5\u00a0Torr \u7684\u4e3b\u771f\u7a7a\u7cfb\u7edf\u4e2d\uff0c\u9ad8\u80fd\u8d28\u5b50\u675f\u901a\u8fc7\u4e0e\u5176\u4e2d\u7684\u7f13\u51b2\u6c14\u4f53\u53d1\u751f\u7535\u8377\u4ea4\u6362\u53cd\u5e94\u6765\u8fdb\u884c\u4e2d\u548c\uff0c\u4ea7\u751f\u9ad8\u80fd\u4e2d\u6027\u539f\u5b50\u675f\u3002\u5728\u8d28\u8c31\u5206\u6790\u4e2d\uff0c\u8fd9\u79cd\u6280\u672f\u7684\u5178\u578b\u5e94\u7528\u5305\u62ec\u6d88\u9664\u201c\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u8d28\u8c31\u201d(ICPMS) \u4e2d\u7684\u8d28\u8c31\u5e72\u6270\uff0c\u6216\u8005\u5728\u4e32\u8054\u8d28\u8c31 (MS-MS) \u4e2d\u4f5c\u4e3a\u78b0\u649e\u5ba4\uff0c\u4ee5\u4fc3\u8fdb\u79bb\u5b50\u5206\u5b50\u53cd\u5e94\u6216\u5f15\u53d1\u788e\u88c2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("charge_exchange_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
