/*
 * corrugated_circular_horn_antenna.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:30 by COMSOL 6.3.0.290. */
public class corrugated_circular_horn_antenna {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("r1", "0.183[m]");
    model.param().descr("r1", "Waveguide, radius");
    model.param().set("fc", "1.841*c_const/2/pi/r1");
    model.param().descr("fc", "Cutoff frequency");
    model.param().set("m_angular", "1");
    model.param().descr("m_angular", "Azimuthal mode number");
    model.param().set("f0", "fc*1.2");
    model.param().descr("f0", "Frequency");
    model.param().set("angle", "17[deg]");
    model.param().descr("angle", "Flare angle");
    model.param().set("grid_y", "0.105[m]");
    model.param().descr("grid_y", "Corrugation, y");
    model.param().set("grid_x", "0.155[m]");
    model.param().descr("grid_x", "Corrugation, x");
    model.param().set("hl", "4[m]");
    model.param().descr("hl", "Horn, length");
    model.param().set("wl", "1[m]");
    model.param().descr("wl", "Waveguide, length");
    model.param().set("ht", "0.5[m]");
    model.param().descr("ht", "Horn, thickness");
    model.param().set("lda0", "c_const/f0");
    model.param().descr("lda0", "Wavelength");
    model.param().set("grid_x1", "0.25[m]");
    model.param().descr("grid_x1", "Matching corrugation length");
    model.param().set("xpol", "5");
    model.param().descr("xpol", "Output cross polarization target percentage");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "hl*cos(angle) + wl*2");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "(hl*cos(angle) + wl*2)/2"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "abs(lda0)", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r1", "wl"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-wl"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "hl*cos(angle)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "r1+hl*sin(angle)", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "hl*cos(angle)", 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r1", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r1+hl*sin(angle)", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "hl*cos(angle)", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r1+hl*sin(angle)+ht", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "hl*cos(angle)", 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r1+ht", 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"grid_x", "grid_y"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2")
         .set("pos", new String[]{"r1-grid_x/2+(grid_y/2+grid_y*5)*tan(angle)", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "grid_y*5", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1")
         .set("linearsize", "floor((hl-grid_y*3.5)*cos(angle)/grid_y/2)");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"grid_y*2*tan(angle)", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "grid_y*2", 1);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"grid_x1", "grid_y"});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("r3")
         .set("pos", new String[]{"r1+grid_x1/2+(-3*grid_y/2+grid_y*5)*tan(angle)", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "grid_y*5", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "0, (-grid_y/2)*tan(angle)*4");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "-grid_y, -grid_y*3");
    model.component("comp1").geom("geom1").feature("copy1").set("keep", false);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"0.3", "wl"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"r1", "-wl"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("arr1", "pol2", "r4");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1");
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(6);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 5);

    model.component("comp1").physics("emw").selection().set(1, 2, 3, 4, 5);
    model.component("comp1").physics("emw").prop("outofplanewavenumber").set("mFloquet", "m_angular");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(4);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("emw").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("emw").prop("MeshControl").set("SizeControlParameter", "UserDefined");
    model.component("comp1").physics("emw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumElementSize", "lda0/5");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").setGenPlots(false);

    model.component("comp1").physics().create("emw2", "ElectromagneticWaves", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/emw2", true);

    model.component("comp1").physics("emw2").selection().set(6);

    model.component("comp1").mesh("mesh1").contribute("physics/emw2", false);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"emw"});
    model.study("std2").feature("stat").set("disabledcoordinatesystems", new String[]{"pml1"});
    model.study("std2").label("Dummy Study for 3D Geometry View");
    model.study("std2").setGenPlots(false);
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").setSolveFor("/physics/emw2", false);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"emw2"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("Revolution 2D Aperture");
    model.result().dataset("rev2").set("hasspacevars", true);
    model.result().dataset("rev2").selection().geom("geom1", 1);
    model.result().dataset("rev2").selection().geom("geom1", 1);
    model.result().dataset("rev2").selection().set(9);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").label("Revolution 2D Feed");
    model.result().dataset("rev3").set("hasspacevars", true);
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection().set(4);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();
    model.sol("sol2").label("3D visualization");

    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").label("Revolution 2D Geometry view");
    model.result().dataset("rev4").set("data", "dset2");
    model.result().dataset("rev4").selection().geom("geom1", 2);
    model.result().dataset("rev4").selection().geom("geom1", 2);
    model.result().dataset("rev4").selection().set(6);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev4").set("startangle", -90);
    model.result().dataset("rev4").set("revangle", 225);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S-parameter (emw)");
    model.result().numerical("gev1").setIndex("expr", "emw.S11dB", 0);
    model.result().numerical("gev1").setIndex("unit", "dB", 0);
    model.result().numerical("gev1").setIndex("descr", "S-parameter, dB, 11 component", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("S-parameter (emw)");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("Waveguide feed x-component integration");
    model.result().numerical("int1").set("data", "rev3");
    model.result().numerical("int1").selection().all();
    model.result().numerical("int1").setIndex("expr", "2*abs(emw.Er*cos(rev3phi)^2+j*emw.Ephi*sin(rev3phi)^2)", 0);
    model.result().numerical("int1").setIndex("descr", "2*abs(emw.Er*cos(rev3phi)^2+j*emw.Ephi*sin(rev3phi)^2)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Waveguide feed x-component integration");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").label("Waveguide feed y-component integration");
    model.result().numerical("int2").set("data", "rev3");
    model.result().numerical("int2").selection().all();
    model.result().numerical("int2").setIndex("expr", "abs(emw.Er*sin(2*rev3phi)-j*emw.Ephi*sin(2*rev3phi))", 0);
    model.result().numerical("int2").setIndex("descr", "abs(emw.Er*sin(2*rev3phi)-j*emw.Ephi*sin(2*rev3phi))", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Waveguide feed y-component integration");
    model.result().numerical("int2").set("table", "tbl3");
    model.result().numerical("int2").setResult();
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").label("Antenna aperture x-component integration");
    model.result().numerical("int3").set("data", "rev2");
    model.result().numerical("int3").selection().all();
    model.result().numerical("int3").setIndex("expr", "2*abs(emw.Er*cos(rev2phi)^2+j*emw.Ephi*sin(rev2phi)^2)", 0);
    model.result().numerical("int3").setIndex("descr", "2*abs(emw.Er*cos(rev2phi)^2+j*emw.Ephi*sin(rev2phi)^2)", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Antenna aperture x-component integration");
    model.result().numerical("int3").set("table", "tbl4");
    model.result().numerical("int3").setResult();
    model.result().numerical().create("int4", "IntSurface");
    model.result().numerical("int4").set("intvolume", true);
    model.result().numerical("int4").label("Antenna aperture y-component integration");
    model.result().numerical("int4").set("data", "rev2");
    model.result().numerical("int4").selection().all();
    model.result().numerical("int4").setIndex("expr", "abs(emw.Er*sin(2*rev2phi)-j*emw.Ephi*sin(2*rev2phi))", 0);
    model.result().numerical("int4").setIndex("descr", "abs(emw.Er*sin(2*rev2phi)-j*emw.Ephi*sin(2*rev2phi))", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("Antenna aperture y-component integration");
    model.result().numerical("int4").set("table", "tbl5");
    model.result().numerical("int4").setResult();
    model.result().create("pg1", "PolarGroup");
    model.result("pg1").run();
    model.result("pg1").label("2D gain pattern plot");
    model.result("pg1").set("manualgrid", true);
    model.result("pg1").set("tspacing", 10);
    model.result("pg1").set("rspacing", 5);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("rp1", "RadiationPattern");
    model.result("pg1").feature("rp1").set("markerpos", "datapoints");
    model.result("pg1").feature("rp1").set("linewidth", "preference");
    model.result("pg1").feature("rp1").set("expr", "emw.gaindBEfar");
    model.result("pg1").feature("rp1").set("phidisc", 180);
    model.result("pg1").feature("rp1").set("normal", new int[]{0, -1, 0});
    model.result("pg1").feature("rp1").set("refdir", new int[]{1, 0, 0});
    model.result("pg1").feature("rp1").set("linewidth", 2);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("data", "none");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").label("Waveguide feed polarization plot");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();

    model.view("view2").label("View Feed");
    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);
    model.view("view2").set("locked", true);
    model.view("view2").camera().set("zoomanglefull", 13);
    model.view("view2").camera().setIndex("position", 0.19221973419189453, 0);
    model.view("view2").camera().setIndex("position", 0, 1);
    model.view("view2").camera().setIndex("position", 137.88841247558594, 2);
    model.view("view2").camera().setIndex("target", 0.19221973419189453, 0);
    model.view("view2").camera().setIndex("target", 0, 1);
    model.view("view2").camera().setIndex("target", -2, 2);
    model.view("view2").camera().setIndex("up", 0, 0);
    model.view("view2").camera().setIndex("up", 1, 1);
    model.view("view2").camera().set("up", new int[]{0, 1, 0});
    model.view("view2").camera().setIndex("rotationpoint", 0.19221973419189453, 0);
    model.view("view2").camera().setIndex("rotationpoint", 0, 1);
    model.view("view2").camera().setIndex("rotationpoint", -2, 2);
    model.view("view2").camera().setIndex("viewoffset", 0, 0);
    model.view("view2").camera().set("viewoffset", new int[]{0, 0});

    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("data", "rev3");
    model.result("pg2").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("arws1").setIndex("expr", "2*emw.Er*cos(rev3phi)", 0);
    model.result("pg2").feature("arws1").setIndex("expr", "-2j*emw.Ephi*sin(rev3phi)", 1);
    model.result("pg2").feature("arws1").setIndex("expr", "2*emw.Ez*cos(rev3phi)", 2);
    model.result("pg2").feature("arws1").create("def1", "Deform");
    model.result("pg2").feature("arws1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg2").run();
    model.result("pg2").feature("arws1").feature("def1").set("expr", new String[]{"50*r", "0", "-1"});
    model.result("pg2").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("arws1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "rev3");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "gray");
    model.result("pg2").feature("line1").create("def1", "Deform");
    model.result("pg2").feature("line1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("def1").set("expr", new String[]{"50*r", "0", "-1"});
    model.result("pg2").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("line1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("Antenna aperture polarization plot");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").set("data", "none");

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);
    model.view("view3").set("locked", true);
    model.view("view3").label("View Aperture");
    model.view("view3").camera().set("zoomanglefull", 13);
    model.view("view3").camera().set("position", new double[]{9.586990356445312, -4, 3});
    model.view("view3").camera().setIndex("position", 0, 1);
    model.view("view3").camera().setIndex("position", -215.67555236816406, 2);
    model.view("view3").camera().set("target", new double[]{0, 0, 4.825225830078125});
    model.view("view3").camera().set("up", new double[]{0, 1, -4.377216100692749E-8});
    model.view("view3").camera().set("rotationpoint", new double[]{0, 0, 4.82521915435791});

    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("data", "rev2");
    model.result("pg3").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("arws1").setIndex("expr", "2*emw.Er*cos(rev2phi)", 0);
    model.result("pg3").feature("arws1").setIndex("expr", "-2j*emw.Ephi*sin(rev2phi)", 1);
    model.result("pg3").feature("arws1").setIndex("expr", "2*emw.Ez*cos(rev2phi)", 2);
    model.result("pg3").feature("arws1").set("arrowtype", "cone");
    model.result("pg3").feature("arws1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arws1").create("def1", "Deform");
    model.result("pg3").feature("arws1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("def1").set("expr", new String[]{"10*r", "0", "1"});
    model.result("pg3").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "rev2");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "gray");
    model.result("pg3").feature("line1").create("def1", "Deform");
    model.result("pg3").feature("line1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("def1").set("expr", new String[]{"10*r", "0", "1"});
    model.result("pg3").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("line1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("3D far-field pattern plot");
    model.result("pg4").set("view", "new");
    model.result("pg4").run();

    model.view("view4").label("View Far-field");
    model.view("view4").camera().set("zoomanglefull", 10);
    model.view("view4").camera().set("position", new double[]{-49.99916076660156, -4, 3});
    model.view("view4").camera().setIndex("position", -10.351726531982422, 1);
    model.view("view4").camera().setIndex("position", -16.160415649414062, 2);
    model.view("view4").camera().set("target", new double[]{0.24882125854492188, 0, 0});
    model.view("view4").camera().setIndex("target", -2.803802490234375E-4, 1);
    model.view("view4").camera().setIndex("target", -0.5169496536254883, 2);
    model.view("view4").camera().set("up", new double[]{-0.31733864545822144, 0, 1});
    model.view("view4").camera().setIndex("up", 0.8196433186531067, 1);
    model.view("view4").camera().setIndex("up", 0.47694873809814453, 2);
    model.view("view4").camera().set("rotationpoint", new double[]{0.24881112575531006, 0, 0});
    model.view("view4").camera().setIndex("rotationpoint", -2.9730796813964844E-4, 1);
    model.view("view4").camera().setIndex("rotationpoint", -0.5169153213500977, 2);
    model.view("view4").camera().set("viewoffset", new double[]{0.00914413295686245, 0});
    model.view("view4").camera().setIndex("viewoffset", -0.004349563270807266, 1);

    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Horn body");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("data", "rev4");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"0", "0", "-hl"});
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").label("Aperture field");
    model.result("pg4").feature("surf2").set("data", "rev2");
    model.result("pg4").feature("surf2")
         .set("expr", "sqrt((2*abs(emw.Er*cos(rev2phi)^2+j*emw.Ephi*sin(rev2phi)^2))^2+abs(emw.Er*sin(2*rev2phi)-j*emw.Ephi*sin(2*rev2phi))^2+abs(emw.Ez*2*cos(rev2phi))^2)");
    model.result("pg4").feature("surf2").set("colortable", "ThermalDark");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature().copy("def1", "pg4/surf1/def1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").label("Feed field");
    model.result("pg4").feature("surf3").set("data", "rev3");
    model.result("pg4").feature("surf3")
         .set("expr", "sqrt((2*abs(emw.Er*cos(rev3phi)^2+j*emw.Ephi*sin(rev3phi)^2))^2+abs(emw.Er*sin(2*rev3phi)-j*emw.Ephi*sin(2*rev3phi))^2+abs(emw.Ez*2*cos(rev3phi))^2)");
    model.result("pg4").feature("surf3").set("colortable", "WaveLight");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature().copy("def1", "pg4/surf2/def1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("data", "dset1");
    model.result("pg4").feature("rp1").set("expr", "emw.normdBEfar/15");
    model.result("pg4").feature("rp1").set("useradiusascolor", false);
    model.result("pg4").feature("rp1").set("colorexpr", "emw.normdBEfar");
    model.result("pg4").feature("rp1").set("thetadisc", 60);
    model.result("pg4").feature("rp1").set("phidisc", 30);
    model.result("pg4").feature("rp1").set("grid", "fine");
    model.result("pg4").feature("rp1").set("gridcolor", "yellow");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegends", false);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("3D Geometry view plot");
    model.result("pg5").set("view", "new");
    model.result("pg5").run();

    model.view("view5").label("View 3D Geometry");
    model.view("view5").camera().set("zoomanglefull", 14.851702690124512);
    model.view("view5").camera().set("position", new double[]{-19.026472091674805, -4, 3});
    model.view("view5").camera().setIndex("position", -24.556575775146484, 1);
    model.view("view5").camera().setIndex("position", 19.296058654785156, 2);
    model.view("view5").camera().set("target", new double[]{0.2700767517089844, 0, 0});
    model.view("view5").camera().setIndex("target", -3.0517578125E-4, 1);
    model.view("view5").camera().setIndex("target", 1.4126262664794922, 2);
    model.view("view5").camera().set("up", new double[]{0.2922270596027374, 0, 1});
    model.view("view5").camera().setIndex("up", 0.40223854780197144, 1);
    model.view("view5").camera().setIndex("up", 0.8676440119743347, 2);
    model.view("view5").camera().set("rotationpoint", new double[]{0.27010154724121094, 0, 0});
    model.view("view5").camera().setIndex("rotationpoint", -2.9730796813964844E-4, 1);
    model.view("view5").camera().setIndex("rotationpoint", 1.412609577178955, 2);
    model.view("view5").camera().set("viewoffset", new double[]{0.025802507996559143, 0});
    model.view("view5").camera().setIndex("viewoffset", -0.06171175092458725, 1);

    model.result("pg5").run();
    model.result("pg5").set("data", "rev4");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("data", "rev4");
    model.result("pg5").feature("vol1").set("expr", "1");
    model.result("pg5").feature("vol1").set("coloring", "uniform");
    model.result("pg5").feature("vol1").set("color", "custom");
    model.result("pg5").feature("vol1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg5").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("vol1").feature("mtrl1").set("family", "iron");

    model.title(null);

    model.description("");

    model.label("corrugated_circular_horn_antenna_embedded.mph");

    model.component("comp1").coordSystem("pml1").set("analysisCaseEdited", true);

    model.result("pg5").run();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("filename", "user:///corrugated_circular_horn_antenna");
    model.result().report("rpt1").set("imagesize", "xsmall");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("title", "\u6ce2\u7eb9\u72b6\u5706\u5f62\u5587\u53ed\u5929\u7ebf\u6a21\u62df\u5668");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u7531\u4e00\u4e2a\u5706\u5f62\u6ce2\u5bfc\u6fc0\u52b1\u7684 TE \u6a21\u6cbf\u7740\u5706\u5f62\u5587\u53ed\u5929\u7ebf\u7684\u6ce2\u7eb9\u72b6\u5185\u8868\u9762\u4f20\u64ad\uff0c\u5176\u4e2d\u8fd8\u4f1a\u4ea7\u751f TM \u6a21\u3002\u5f53\u8fd9\u4e24\u79cd\u6a21\u7ec4\u5408\u5728\u4e00\u8d77\u65f6\uff0c\u5929\u7ebf\u5b54\u5f84\u5904\u4f1a\u4ea7\u751f\u8f83\u4f4e\u7684\u4ea4\u53c9\u6781\u5316\u3002\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u4fee\u6539\u5929\u7ebf\u7684\u51e0\u4f55\u7ed3\u6784\uff0c\u6539\u8fdb\u5929\u7ebf\u7684\u8f90\u5c04\u7279\u6027\u548c\u5b54\u5f84\u7684\u4ea4\u53c9\u6781\u5316\u6bd4\u3002");

    return model;
  }

  public static Model run2(Model model) {
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2")
         .label("\u4e09\u7ef4\u51e0\u4f55\u89c6\u56fe\u7ed8\u56fe");
    model.result().report("rpt1").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("pg1").set("noderef", "pg5");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u8ba1\u7b97 S \u53c2\u6570 (S11)");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").label("\u4ea4\u53c9\u6781\u5316\u7387");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("field1", "StringDataField");
    model.result().report("rpt1").feature("sec3").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").label("\u7ed8\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1")
         .label("\u5929\u7ebf\u589e\u76ca\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2")
         .label("\u6ce2\u5bfc\u9988\u7535\u6781\u5316\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature()
         .create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec1")
         .label("\u6ce2\u5bfc\u9988\u7535 x \u5206\u91cf\u79ef\u5206");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec1").feature()
         .create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec1").feature("mtbl1")
         .set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature()
         .create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec2")
         .label("\u6ce2\u5bfc\u9988\u7535 y \u5206\u91cf\u79ef\u5206");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec2").feature()
         .create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec2").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3")
         .label("\u5929\u7ebf\u5b54\u5f84\u6781\u5316\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature()
         .create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec1")
         .label("\u5929\u7ebf\u5b54\u5f84 x \u5206\u91cf\u79ef\u5206");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec1").feature()
         .create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec1").feature("mtbl1")
         .set("noderef", "tbl4");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature()
         .create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec2")
         .label("\u5929\u7ebf\u5b54\u5f84 y \u5206\u91cf\u79ef\u5206");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec2").feature()
         .create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec3").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl5");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4")
         .label("\u4e09\u7ef4\u8fdc\u573a\u6a21\u5f0f\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("sec4").feature("pg1")
         .set("noderef", "pg4");

    model.title("\u6ce2\u7eb9\u72b6\u5706\u5f62\u5587\u53ed\u5929\u7ebf");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5305\u542b\u7528\u4e8e\u5bfc\u822a\u800c\u975e\u529f\u80fd\u533a\u7684\u5927\u6309\u94ae\u7684\u5de5\u5177\u680f\n\u2022 \u7528\u4f5c\u8282\u548c\u8282\u6807\u9898\u7684\u5b50\u8868\u5355\u5305\u542b\u56fe\u50cf\n\u2022 \u5982\u679c\u7ed3\u679c\u5728\u4e00\u5b9a\u8303\u56f4\u5185\uff0c\u5219\u63d0\u4f9b\u76f8\u5173\u4fe1\u606f\n\u2022 \u4ee5\u5168\u4e09\u7ef4\u7684\u5f62\u5f0f\u53ef\u89c6\u5316\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\n\n\u7531\u4e00\u4e2a\u5706\u5f62\u6ce2\u5bfc\u6fc0\u52b1\u7684 TE \u6a21\u6cbf\u7740\u5706\u5f62\u5587\u53ed\u5929\u7ebf\u7684\u6ce2\u7eb9\u72b6\u5185\u8868\u9762\u4f20\u64ad\u65f6\u4f1a\u4ea7\u751f TM \u6a21\u3002\u5f53\u8fd9\u4e24\u79cd\u6a21\u7ec4\u5408\u5728\u4e00\u8d77\u65f6\uff0c\u53ef\u4ee5\u964d\u4f4e\u5929\u7ebf\u5b54\u5f84\u5904\u7684\u4ea4\u53c9\u6781\u5316\u3002\u60a8\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u6765\u4fee\u6539\u5929\u7ebf\u7684\u51e0\u4f55\u7ed3\u6784\uff0c\u4ece\u800c\u6539\u8fdb\u5929\u7ebf\u7684\u8f90\u5c04\u7279\u6027\u548c\u5b54\u5f84\u7684\u4ea4\u53c9\u6781\u5316\u6bd4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("corrugated_circular_horn_antenna.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
