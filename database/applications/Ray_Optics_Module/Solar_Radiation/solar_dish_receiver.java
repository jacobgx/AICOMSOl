/*
 * solar_dish_receiver.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class solar_dish_receiver {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Solar_Radiation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("f", "3[m]");
    model.param().descr("f", "\u7126\u8ddd");
    model.param().set("phi", "45[deg]");
    model.param().descr("phi", "\u8fb9\u7f18\u89d2");
    model.param().set("d", "4*f*(csc(phi)-cot(phi))");
    model.param().descr("d", "\u789f\u72b6\u7ed3\u6784\u76f4\u5f84");
    model.param().set("A", "pi*d^2/4");
    model.param().descr("A", "\u789f\u72b6\u7ed3\u6784\u6295\u5f71\u8868\u9762\u79ef");
    model.param().set("psim", "4.65[mrad]");
    model.param().descr("psim", "\u592a\u9633\u5706\u9762\u6700\u5927\u89d2\u5ea6");
    model.param().set("sig", "1.75[mrad]");
    model.param().descr("sig", "\u8868\u9762\u659c\u7387\u8bef\u5dee");
    model.param().set("I0", "1[kW/m^2]");
    model.param().descr("I0", "\u592a\u9633\u8f90\u7167\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "30[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "100[mm]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\paraboloidal_reflector_shell_3d.mph", new String[]{"part2"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "phi", "phi");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "F", "3[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", -1);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "-f"});
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_unisel1", true);

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_pi1_unisel1");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "solar_dish_receiver_reference.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").setIndex("argunit", "mm", 0);
    model.component("comp1").func("int1").setIndex("fununit", "W/mm^2", 0);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7126\u5e73\u9762");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(5);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("r", "sqrt(x^2+y^2)", "xy \u5e73\u9762\u7684\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable("var1").set("theta", "atan2(y,x)", "xy \u5e73\u9762\u7684\u65b9\u4f4d\u89d2");
    model.component("comp1").variable("var1")
         .set("dx", "x-dest(x)", "\u4ece\u76ee\u6807\u5230\u6e90\u7684\u4f4d\u79fb\uff0cx \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("dy", "y-dest(y)", "\u4ece\u76ee\u6807\u5230\u6e90\u7684\u4f4d\u79fb\uff0cy \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("dz", "z-dest(z)", "\u4ece\u76ee\u6807\u5230\u6e90\u7684\u4f4d\u79fb\uff0cz \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("dxn", "sqrt(dx^2+dy^2+dz^2)", "\u76ee\u6807\u5230\u6e90\u7684\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("dxcn", "sqrt(x^2+y^2+z^2)", "\u63a5\u6536\u5668\u4e2d\u5fc3\u5230\u6e90\u7684\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("delta", "acos((dx*x+dy*y+dz*z)/(dxn*dxcn))", "\u5165\u5c04\u7ebf\u7684\u592a\u9633\u89d2");
    model.component("comp1").variable("var1")
         .set("fd", "1000[W/m^2]/(pi*sin(4.65[mrad])^2)*(delta<4.65[mrad])", "\u8f90\u5c04\u5f3a\u5ea6");
    model.component("comp1").variable("var1")
         .set("theta1", "acos((dx*nx+dy*ny+dz*nz)/dxn)", "\u53cd\u5c04\u7ebf\u4e0e\u6cd5\u7ebf\u7684\u5939\u89d2\uff0c\u789f\u5f0f");
    model.component("comp1").variable("var1")
         .set("theta2", "acos((dx*dest(nx)+dy*dest(ny)+dz*dest(nz))/dxn)", "\u53cd\u5c04\u7ebf\u4e0e\u6cd5\u7ebf\u7684\u5939\u89d2\uff0c\u76ee\u6807");
    model.component("comp1").variable("var1")
         .set("cr", "intop1(fd*cos(theta1)*cos(theta2)/dxn^2)/1[kW/m^2]", "\u7406\u60f3\u805a\u5149\u6bd4\uff0cJeter");

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genproj1").selection().named("sel1");
    model.component("comp1").cpl("genproj1").set("srcmap", new String[]{"r", "theta", "z"});
    model.component("comp1").cpl("genproj1").set("dstmap", new String[]{"r", "y"});

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputePower", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").create("ill1", "IlluminatedSurface", 2);
    model.component("comp1").physics("gop").feature("ill1").label("\u7406\u60f3\u53d7\u7167\u9762");
    model.component("comp1").physics("gop").feature("ill1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("gop").feature("ill1").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("ill1").setIndex("Nr", 100000, 0);
    model.component("comp1").physics("gop").feature("ill1").set("Li", new int[]{0, 0, -1});
    model.component("comp1").physics("gop").feature("ill1").set("FiniteSource", "SampleFromDistribution");
    model.component("comp1").physics("gop").feature("ill1").set("Psrc", "A*I0");
    model.component("comp1").physics("gop").feature("ill1").set("psim", "psim");
    model.component("comp1").physics("gop").create("ill2", "IlluminatedSurface", 2);
    model.component("comp1").physics("gop").feature("ill2").label("\u771f\u5b9e\u53d7\u7167\u9762");
    model.component("comp1").physics("gop").feature("ill2").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("gop").feature("ill2").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("ill2").setIndex("Nr", 100000, 0);
    model.component("comp1").physics("gop").feature("ill2").set("Li", new int[]{0, 0, -1});
    model.component("comp1").physics("gop").feature("ill2").set("alpha", 0.1);
    model.component("comp1").physics("gop").feature("ill2").set("FiniteSource", "SampleFromDistribution");
    model.component("comp1").physics("gop").feature("ill2").set("psim", "psim");
    model.component("comp1").physics("gop").feature("ill2").set("LimbDarkeningModel", "EmpiricalPowerLaw");
    model.component("comp1").physics("gop").feature("ill2").set("IncludeSurfaceRoughness", true);
    model.component("comp1").physics("gop").feature("ill2").set("sigmaphi", "sig");
    model.component("comp1").physics("gop").feature("ill2").set("Psrc", "A*I0");
    model.component("comp1").physics("gop").feature("ill2").set("InitialPolarizationType", "UnPolarized");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u7126\u5e73\u9762");
    model.component("comp1").physics("gop").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("gop").feature("wall1").create("bsrc1", "DepositedRayPowerBoundary", 2);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "5E-4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "2E-4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 4");
    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"gop/ill2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "4[m]/c_const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").set("extrasteps", "none");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5c04\u7ebf\u8f68\u8ff9\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.Q");
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u5c04\u7ebf\u529f\u7387");
    model.result("pg1").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("param", "xy");
    model.result().dataset("surf1").selection().named("sel1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u6c89\u79ef\u529f\u7387\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6c89\u79ef\u529f\u7387\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "gop.wall1.bsrc1.Qp");
    model.result("pg2").feature("surf1").set("descr", "\u8fb9\u754c\u70ed\u6e90");
    model.result("pg2").feature("surf1").set("unit", "W/mm^2");
    model.result("pg2").feature("surf1").set("colortable", "GrayBody");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "1E-4", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.03-1E-4", 1, 0);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u805a\u5149\u6bd4\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u805a\u5149\u6bd4\uff0c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "genproj1(gop.wall1.bsrc1.Qp)/genproj1(I0)");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "\u5c04\u7ebf\u8ffd\u8e2a", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "cr");
    model.result("pg3").feature("lngr2").set("titletype", "none");
    model.result("pg3").feature("lngr2").setIndex("legends", "Jeter", 0);
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "0 4");
    model.study("std2").feature("rtrac").set("useadvanceddisable", true);
    model.study("std2").feature("rtrac").set("disabledphysics", new String[]{"gop/ill1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol2").feature("t1").set("timestepgenalpha", "4[m]/c_const");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol2");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "ray2");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg4").create("rtrj1", "RayTrajectories");
    model.result("pg4").feature("rtrj1").set("linetype", "line");
    model.result("pg4").feature("rtrj1").set("extrasteps", "none");
    model.result("pg4").feature("rtrj1").create("col1", "Color");
    model.result("pg4").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg4").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg4").run();
    model.result("pg4").label("\u5c04\u7ebf\u8f68\u8ff9\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5c04\u7ebf\u8f68\u8ff9\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg4").run();
    model.result("pg4").feature("rtrj1").feature("col1").set("expr", "gop.Q");
    model.result("pg4").run();
    model.result().dataset().duplicate("surf2", "surf1");
    model.result().dataset("surf2").set("data", "dset2");
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset2");
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u6c89\u79ef\u529f\u7387\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg5").set("title", "\u6c89\u79ef\u529f\u7387\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg5").set("data", "surf2");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").label("\u805a\u5149\u6bd4\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg6").set("title", "\u805a\u5149\u6bd4\uff0c\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg6").set("data", "cln2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "int1(r)/I0");
    model.result("pg6").feature("lngr2").setIndex("legends", "Shuai", 0);
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().duplicate("pg7", "pg2");
    model.result("pg7").run();
    model.result("pg7").label("\u6c89\u79ef\u529f\u7387\uff0c\u771f\u5b9e\u548c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg7")
         .set("title", "\u6c89\u79ef\u529f\u7387\uff0c\u771f\u5b9e\u548c\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").feature().copy("surf2", "pg5/surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("data", "surf2");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").create("trn1", "Transformation");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("trn1").set("move", new String[]{"0.07[m]", "0"});
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("ann1", "Annotation");
    model.result("pg7").feature("ann1").set("text", "\u7406\u60f3\u53cd\u5c04\u5668");
    model.result("pg7").feature("ann1").set("posxexpr", -0.015);
    model.result("pg7").feature("ann1").set("posyexpr", 0.038);
    model.result("pg7").feature("ann1").set("showpoint", false);
    model.result("pg7").feature("ann1").set("showframe", true);
    model.result("pg7").run();
    model.result("pg7").create("ann2", "Annotation");
    model.result("pg7").feature("ann2").set("text", "\u771f\u5b9e\u53cd\u5c04\u5668");
    model.result("pg7").feature("ann2").set("posxexpr", 0.055);
    model.result("pg7").feature("ann2").set("posyexpr", 0.038);
    model.result("pg7").feature("ann2").set("showpoint", false);
    model.result("pg7").feature("ann2").set("showframe", true);
    model.result("pg7").run();

    model.title("\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668");

    model
         .description("\u629b\u7269\u9762\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668\u53ef\u4ee5\u5c06\u592a\u9633\u80fd\u805a\u7126\u5230\u4e00\u4e2a\u76ee\u6807\uff08\u63a5\u6536\u5668\uff09\uff0c\u4ea7\u751f\u5f88\u9ad8\u7684\u5c40\u90e8\u70ed\u901a\u91cf\uff0c\u53ef\u7528\u4e8e\u4ea7\u751f\u5411\u53d1\u7535\u673a\u4f9b\u7535\u7684\u70ed\u6d41\uff0c\u6216\u7528\u4e8e\u751f\u6210\u53ef\u76f4\u63a5\u7528\u4f5c\u71c3\u6599\u6e90\u7684\u6c22\u3002\u672c\u4f8b\u8ba1\u7b97\u5230\u8fbe\u63a5\u6536\u5668\u7684\u70ed\u901a\u91cf\u968f\u5f84\u5411\u4f4d\u7f6e\u7684\u53d8\u5316\u60c5\u51b5\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u53d1\u5e03\u7684\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002\u7531\u4e8e\u592a\u9633\u7684\u5c3a\u5bf8\u6709\u9650\u4e14\u5b58\u5728\u4e34\u8fb9\u660f\u6697\uff0c\u8fd8\u8003\u8651\u4e86\u789f\u9762\u7684\u8868\u9762\u7c97\u7cd9\u5ea6\uff0c\u5bf9\u8ba1\u7b97\u5f97\u5230\u7684\u503c\u8fdb\u884c\u4e86\u9002\u5f53\u6821\u6b63\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("solar_dish_receiver.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
