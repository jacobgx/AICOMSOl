/*
 * turbomolecular_pump_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:56 by COMSOL 6.3.0.290. */
public class turbomolecular_pump_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Vacuum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Nb", "36", "\u53f6\u7247\u6570");
    model.param().set("psi", "2*pi[rad]/Nb", "\u6247\u5f62\u89d2");
    model.param().set("rtip", "100[mm]", "\u9f7f\u9876\u5706\u534a\u5f84");
    model.param().set("rroot", "0.8*rtip", "\u6839\u90e8\u534a\u5f84");
    model.param().set("rrms", "sqrt((rroot^2+rtip^2)/2)", "\u5747\u65b9\u6839\u534a\u5f84");
    model.param()
         .set("S", "2*rrms*sin(psi/2)", "\u5747\u65b9\u6839\u534a\u5f84\u65f6\u7684\u53f6\u7247\u95f4\u8ddd");
    model.param().set("B", "S", "\u53f6\u7247\u957f\u5ea6\uff0cS0=S/B=1");
    model.param().set("alpha", "30[deg]", "\u53f6\u7247\u89d2\u5ea6");
    model.param().set("rh", "B*sin(alpha)", "\u884c\u9ad8");
    model.param().set("wp11x", "rroot", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 1\uff0cx \u5750\u6807");
    model.param().set("wp11y", "0", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 1\uff0cy \u5750\u6807");
    model.param().set("wp11z", "0", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 1\uff0cz \u5750\u6807");
    model.param().set("wp12x", "rtip", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 2\uff0cx \u5750\u6807");
    model.param().set("wp12y", "0", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 2\uff0cy \u5750\u6807");
    model.param().set("wp12z", "0", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 2\uff0cz \u5750\u6807");
    model.param().set("wp13x", "rroot", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 3\uff0cx \u5750\u6807");
    model.param().set("wp13y", "1[cm]", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 3\uff0cy \u5750\u6807");
    model.param().set("wp13z", "1[cm]*tan(alpha)", "\u5de5\u4f5c\u5e73\u9762 1\uff0c\u70b9 3\uff0cz \u5750\u6807");
    model.param()
         .set("wp21x", "wp11x*cos(psi)-wp11y*sin(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 1\uff0cx \u5750\u6807");
    model.param()
         .set("wp21y", "wp11x*sin(psi)+wp11y*cos(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 1\uff0cy \u5750\u6807");
    model.param().set("wp21z", "wp11z", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 1\uff0cz \u5750\u6807");
    model.param()
         .set("wp22x", "wp12x*cos(psi)-wp12y*sin(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 2\uff0cx \u5750\u6807");
    model.param()
         .set("wp22y", "wp12x*sin(psi)+wp12y*cos(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 2\uff0cy \u5750\u6807");
    model.param().set("wp22z", "wp12z", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 2\uff0cz \u5750\u6807");
    model.param()
         .set("wp23x", "wp13x*cos(psi)-wp13y*sin(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 3\uff0cx \u5750\u6807");
    model.param()
         .set("wp23y", "wp13x*sin(psi)+wp13y*cos(psi)", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 3\uff0cy \u5750\u6807");
    model.param().set("wp23z", "wp13z", "\u5de5\u4f5c\u5e73\u9762 2\uff0c\u70b9 3\uff0cz \u5750\u6807");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rtip");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "rh");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-rh/2"});
    model.component("comp1").geom("geom1").feature("cyl1").set("rot", 30);
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "rroot");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "coordinates");
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp11x", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp11y", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp11z", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp12x", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp12y", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp12z", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp13x", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp13y", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").setIndex("genpoints", "wp13z", 2, 2);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "coordinates");
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp21x", 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp21y", 0, 1);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp21z", 0, 2);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp22x", 1, 0);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp22y", 1, 1);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp22z", 1, 2);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp23x", 2, 0);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp23y", 2, 1);
    model.component("comp1").geom("geom1").feature("wp2").setIndex("genpoints", "wp23z", 2, 2);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par2", 7);
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par2", 1, 2, 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("del1", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("del1", 4);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6839\u90e8\u58c1");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("del1", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5c16\u7aef\u58c1");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("del1", 6);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u53f6\u7247\u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("del1", 2, 5);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6240\u6709\u8fd0\u52a8\u58c1");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel3", "sel5"});

    model.title(null);

    model.description("");

    model.label("turbomolecular_pump_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
